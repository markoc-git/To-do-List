package com.example.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DBManager extends SQLException {

    DatabaseHelper helper;
    Context context;
    SQLiteDatabase database;

    public DBManager(Context context) {
        this.context = context;
    }


    public void open(){
        helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();
    }


    public Cursor cursor(){
        String[] columns = new String[]{
                DatabaseHelper._ID,
                DatabaseHelper.TITLE,
                DatabaseHelper.DESC
        };

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void insert(String name,String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE,name);
        contentValues.put(DatabaseHelper.DESC,desc);
        database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public int update(long _id,String name,String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE,name);
        contentValues.put(DatabaseHelper.DESC,description);
        return database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" +  _id,null);
    }

}
