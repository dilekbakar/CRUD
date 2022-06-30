package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table Kullanici (AD TEXT primary key, SOYAD TEXT, TELEFON TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {

        db.execSQL("drop Table if exists Kullanici");
    }

    public boolean insertData(String ad, String soyad, String telefon ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("AD", ad);
        cv.put("SOYAD", soyad);
        cv.put("TELEFON", telefon);
        long result = db.insert("Kullanici",null,cv);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean updateData(String ad, String soyad, String telefon ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("SOYAD", soyad);
        cv.put("TELEFON", telefon);
        Cursor cursor = db.rawQuery("Select * from Kullanici where AD=?",new String[]{ad});

        if (cursor.getCount()>0) {
            long result = db.update("Kullanici", cv, "AD=?", new String[]{ad});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public boolean deleteData(String ad ){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Kullanici where AD=?",new String[]{ad});

        if (cursor.getCount()>0) {
            long result = db.delete("Kullanici", "AD=?", new String[]{ad});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Kullanici ",null);
        return cursor;
    }
}
