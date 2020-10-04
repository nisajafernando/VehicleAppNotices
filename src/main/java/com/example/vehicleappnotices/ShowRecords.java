// IT19170176
// FERNANDO W.N.D
// CarMart Notices

package com.example.vehicleappnotices;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShowRecords extends AppCompatActivity {
    public static  final String CHANNEL_ID="channel1";
   FloatingActionButton fab;
   ActionBar actionBar;
   RecyclerView mRecyclerView;
   DatabaseHelper databaseHelper;
   Adapter recyclerAdapter;
   List<Model> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_record);

      arrayList = new ArrayList<>();
      actionBar = getSupportActionBar();
      actionBar.setTitle("All Information");

      mRecyclerView = findViewById(R.id.recyclerView);


      databaseHelper = new DatabaseHelper(this);
      showRecord();

        fab = findViewById(R.id.addFabButton);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notificationMsg ="Hello Welcome to the MAD team";

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(ShowRecords.this, AddRecordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(ShowRecords.this, 0, intent, 0);
                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(ShowRecords.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("-CarMart-")
                        .setContentText("Enter your Vehicle App Notice")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
// Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ShowRecords.this);
// notificationId is a unique int for each notification that you must define
                notificationManager.notify(0, builder.build());
               // Intent intent = new Intent(ShowRecords.this, AddRecordActivity.class);
                intent.putExtra("editMode",false);
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();


       return super.onCreateOptionsMenu(menu);
    }
    //search

    private void showRecord() {

        Adapter adapter = new Adapter(ShowRecords.this, databaseHelper.getAllData(Constants.C_ADD_NOTICE + " DESC"));
        //last added record will be show on top-record sorting
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }

    @Override

    //this funvtion kills all activites.. so
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}