package com.example.umair_191092;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQlite extends SQLiteOpenHelper {
    private Context Cont;
    private static final String DATABASE_NAME = "Details.db";
    private static int DATABASE_VERSION = 1;
    private static  String TABLE_NAME = "info";
    private String query_create =" create table " + TABLE_NAME + "(id TEXT primary key,"+
            "name TEXT, city TEXT, age TEXT)";
    public SQlite( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Cont = context;
        Toast.makeText(context, "Database created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_create);
        Toast.makeText(Cont, "Table created ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertstudentdata (String s_id, String s_name, String s_age, String  s_city) {
        SQLiteDatabase sql_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", s_id);
        contentValues.put("name", s_name);
        contentValues.put("city", s_city);
        contentValues.put("age", s_age);
        long insertCheck = sql_db.insert(TABLE_NAME, null, contentValues);
        if (insertCheck == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getstudentdata(){
        SQLiteDatabase sql_db =this.getWritableDatabase();
        Cursor cursor = sql_db.rawQuery("select * from "+ TABLE_NAME,null);
        return cursor;
    }

}

