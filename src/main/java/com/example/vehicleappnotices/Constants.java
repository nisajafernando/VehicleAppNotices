// IT19170176
// FERNANDO W.N.D
// CarMart Notices


package com.example.vehicleappnotices;

public class Constants {
    //db name
    public static final String DB_NAME ="NOTICE_INFO_DB";
    //db version
    public static final int DB_VERSION =1;
    //db table
    public static final String TABLE_NAME ="NOTICE_INFO_TABLE";
    //table columns
    public static final String C_ID ="ID";
    public static final String C_HEADING ="HEADING";
    public static final String C_NAME ="NAME";
    public static final String C_MOBILE ="MOBILE";
    public static final String C_EMAIL ="EMAIL";
    public static final String C_IMAGE ="IMAGE";
    public static final String C_NOTICE_INFO ="NOTICE_INFO";
    public static final String C_ADD_NOTICE ="ADD_NOTICE";
    public static final String C_UPDATE_NOTICE ="UPDATE_NOTICE";
    //crate query for table
    public static final String CREATE_TABLE ="CREATE TABLE "+ TABLE_NAME +" ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_HEADING +" TEXT,"
            + C_NAME + " TEXT,"
            + C_MOBILE +" TEXT,"
            + C_EMAIL +" TEXT,"
            + C_IMAGE +" TEXT,"
            + C_NOTICE_INFO +" TEXT,"
            + C_ADD_NOTICE +" TEXT,"
            + C_UPDATE_NOTICE +" TEXT"
            + ");";

}
