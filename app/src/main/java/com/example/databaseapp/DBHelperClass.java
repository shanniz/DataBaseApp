package com.example.databaseapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelperClass {
    private SQLiteDatabase mSQLiteDatabase;

    public DBHelperClass(SQLiteDatabase Database) {
        mSQLiteDatabase = Database;
    }

    public void createTable(String tableName, String col1, String col2){
        String q = "CREATE TABLE IF NOT EXISTS " + tableName + " ("  + col1 + "VARCHAR, "+ col2 +" VARCHAR );";
        mSQLiteDatabase.execSQL(q);
    }

    public void insertData(String tableName, String data1, String data2){
        String q = "INSERT INTO " + tableName + " VALUES( '"+ data1 +"', '"+data2+"' )";
        mSQLiteDatabase.execSQL(q);
    }
    public Cursor getData(String tableName){
        String q= "SELECT * FROM " + tableName;
        Cursor cursor = mSQLiteDatabase.rawQuery(q, null);
        return cursor;
    }
}
