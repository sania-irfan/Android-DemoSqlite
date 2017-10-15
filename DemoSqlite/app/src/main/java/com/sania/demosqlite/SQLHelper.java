package com.sania.demosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sania on 31/10/2016.
 */

public class SQLHelper {

    public static final String COL_1="NAME";
    public static final String COL_2="ADDRESS";
    public static final String COL_3="PASS";

    static SQLiteDatabase db = LocalDatabase.db;

    public static void SetupDB() {

        db.execSQL("CREATE TABLE IF NOT EXISTS Contact(NAME TEXT PRIMARY KEY,ADDRESS TEXT,PASS TEXT)");
    }

    public static void setContact() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", "1");
        db.insert("Contact", null, contentValues);
    }


    public static boolean checkContact(){
        Cursor cursor = db.rawQuery("select * from Contact", null);
        if (cursor.getCount()==0){
            cursor.close();
            return false;
        }else {
            cursor.close();
            return true;
        }
    }
    public static void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
       // db.execSQL("DROP TABLE IF EXISTS");
        //onCreate(db);
    }

    public static boolean insertData(String name,String address,String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,address);
        contentValues.put(COL_3,password);
        long result=db.insert("Contact",null,contentValues);
        if(result==-1)
            return false;
            else
            return true;
    }
    public static Cursor getAllData(){
        Cursor result=db.rawQuery("select * from Contact",null);
        return result;
    }



}