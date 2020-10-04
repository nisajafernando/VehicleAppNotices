// IT19170176
// FERNANDO W.N.D
// CarMart Notices

package com.example.vehicleappnotices;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import  androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class AddRecordActivity extends AppCompatActivity {

     ImageView pImageView;
     EditText pheadingEt,pnameEt,pmobileEt,pemailEt,pprovinceEt,pnotice_infoEt;
    Button saveInfoBt;
    ActionBar actionBar;
    AwesomeValidation awesomeValidation;



    private static  final int  CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE =101;

    private static final int IMAGE_PICK_CAMERA_CODE=102;
    private static final int IMAGE_PICK_GALLERY_CODE=103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;
    private String heading, name, mobile, email,province, notice_info, timeStamp;
    private  DatabaseHelper dbHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_record);


        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Information");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        pImageView = findViewById(R.id.image);
        pheadingEt = findViewById(R.id.heading);
        pnameEt = findViewById(R.id.name);
        pmobileEt = findViewById(R.id.mobile);
        pemailEt = findViewById(R.id.email);
        pprovinceEt=findViewById(R.id.province);
        // Setup field validators.
        //mEmailValidator = new EmailValidator();
        //pemailEt.addTextChangedListener(mEmailValidator);

        pnotice_infoEt = findViewById(R.id.notice_info);
        saveInfoBt = findViewById(R.id.add_notice);




        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //initialize Validation Style


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for name
        awesomeValidation.addValidation(this,R.id.name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //For  mobile Number
        //awesomeValidation.addValidation(this,R.id.mobile,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);
        awesomeValidation.addValidation(this,R.id.mobile, RegexTemplate.NOT_EMPTY,R.string.invalid_mobile);
        //For Email
          awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //For notice information
        awesomeValidation.addValidation(this,R.id.notice_info, RegexTemplate.NOT_EMPTY,R.string.invalid_notice);



        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check Validation
                if(awesomeValidation.validate()){
                    //on success

                    Toast.makeText(getApplicationContext(),"Form Validate Successfully",Toast.LENGTH_SHORT).show();
                    //when click on save button insert the data to db
                    getData();
                    startActivity(new Intent(AddRecordActivity.this, ShowRecords.class));
                    Toast.makeText(AddRecordActivity.this,"Add Successfully",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Validation Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //initialize database object in  main function
        dbHelper = new DatabaseHelper(this);

        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });
    }

    private void getData() {
        heading =""+pheadingEt.getText().toString().trim();
        name = ""+pnameEt.getText().toString().trim();
        mobile = ""+pmobileEt.getText().toString().trim();
        email = ""+pemailEt.getText().toString().trim();

        notice_info = ""+pnotice_infoEt.getText().toString().trim();

        timeStamp = ""+System.currentTimeMillis();


        dbHelper.insertInfo(
                ""+heading,
                ""+name,
                ""+mobile,
                ""+email,
                ""+imageUri,
                ""+notice_info,
                ""+timeStamp,
                ""+timeStamp
        );}


    private void imagePickDialog() {

        String[] options ={"Camera", "Gallery"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //if 0 then open the camera and also check the permission of camera
                    if (!checkCameraPermission()){
                        //If permission not granted  then request for camera permission
                        requestCameraPermission();
                    }
                    else{
                        pickFromCamera();
                    }

                }
                else if (which==1) {
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else{
                        pickFromStorage();
                    }
                }

            }
        });
        builder.create().show();
    }

    private void pickFromStorage() {
        //so this function get image from gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        //now get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image description");

        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==  (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private  void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private  boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);
        boolean resultl = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result && resultl;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted =grantResults[0] ==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted =grantResults[1] ==PackageManager.PERMISSION_GRANTED;


                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else{
                        Toast.makeText(this,"Camera permission required!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromStorage();
                    }
                    else {
                        Toast.makeText(this,"Storage Permission Required!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    pImageView.setImageURI(resultUri);
                }
                else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this,""+error, Toast.LENGTH_SHORT).show();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onSupportNavigateUp() {
        /* this moves add record activity to main activity */
        onBackPressed();
        return super.onSupportNavigateUp();

    }
}