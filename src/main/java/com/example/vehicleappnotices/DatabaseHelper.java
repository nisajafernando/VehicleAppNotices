// IT19170176
// FERNANDO W.N.D
// CarMart Notices


package com.example.vehicleappnotices;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }
    //insert info function IT19170176 Fernando W.N.D.

    public long insertInfo(String heading,String name,String mobile,String email,String image,String notice_info,String add_notice,String update_notice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_HEADING,heading);
        values.put(Constants.C_NAME,name);
        values.put(Constants.C_MOBILE,mobile);
        values.put(Constants.C_EMAIL,email);
        values.put(Constants.C_IMAGE,image);
        values.put(Constants.C_NOTICE_INFO,notice_info);
        values.put(Constants.C_ADD_NOTICE,add_notice);
        values.put(Constants.C_UPDATE_NOTICE,update_notice);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    //update info function  IT19170176 Fernando W.N.D.
    public void updateInfo(String id, String heading, String name, String mobile, String email, String image, String notice_info, String add_notice, String update_notice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_HEADING,heading);
        values.put(Constants.C_NAME,name);
        values.put(Constants.C_MOBILE,mobile);
        values.put(Constants.C_EMAIL,email);
        values.put(Constants.C_IMAGE,image);
        values.put(Constants.C_NOTICE_INFO,notice_info);
        values.put(Constants.C_ADD_NOTICE,add_notice);
        values.put(Constants.C_UPDATE_NOTICE,update_notice);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }

   //delete info function IT19170176 Fernando W.N.D.
    public void deleteInfo(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME,Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }


  //get data to the recycler view using a arrayList IT19170176 Fernando W.N.D.
    public ArrayList<Model> getAllData(String orderBy){
        ArrayList<Model> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //when we select all info from database
        if(cursor.moveToNext()){
                do {
                    //do is used because first it gets the data from columns then move to next condition
                    Model model = new Model(
                            "" + cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                            "" + cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                            "" + cursor.getString(cursor.getColumnIndex(Constants.C_HEADING)),
                            "" + cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                            "" + cursor.getString(cursor.getColumnIndex(Constants.C_MOBILE)),
                            "" + cursor.getString(cursor.getColumnIndex(Constants.C_EMAIL)),
                            ""+cursor.getString(cursor.getColumnIndex(Constants.C_NOTICE_INFO)),
                            ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADD_NOTICE)),
                            ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATE_NOTICE))

                    );
                    arrayList.add(model);
                }
            while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
        }
    }

