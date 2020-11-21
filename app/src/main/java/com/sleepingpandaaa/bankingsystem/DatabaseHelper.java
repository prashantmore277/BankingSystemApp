package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9845689545,'Rahul',50000,'rahullod@gmail.com','XXXXXXXXXXXX1234','ABC01551515')");
        db.execSQL("insert into user_table values(9260357070,'Piyush',40000,'piyushchuu@gmail.com','XXXXXXXXXXXX2341','BCA59568645')");
        db.execSQL("insert into user_table values(8008445655,'Pravin',29000,'pravinpari@gmail.com','XXXXXXXXXXXX3412','ERY15555332')");
        db.execSQL("insert into user_table values(9895292221,'Gaurav',65000.01,'gaurav@gmail.com','XXXXXXXXXXXX4123','JJJ88336992')");
        db.execSQL("insert into user_table values(8888105633,'Kunal',46303.48,'kunalDev@gmail.com','XXXXXXXXXXXX2345','PLI66645441')");
        db.execSQL("insert into user_table values(8888115151,'Bhushan',44555.16,'dashingbhushan@gmail.com','XXXXXXXXXXXX3452','OPP449955322')");
        db.execSQL("insert into user_table values(3603703695,'Rihan',43333.00,'rihanashu@gmail.com','XXXXXXXXXXXX4523','NAR36578945')");
        db.execSQL("insert into user_table values(9890636363,'Mangesh',55155.22,'mangeshharal2001@gmail.com','XXXXXXXXXXXX5234','VIV48847596')");
        db.execSQL("insert into user_table values(9845681155,'Pikachu',43986.46,'thunderpikachu@gmail.com','XXXXXXXXXXXX3456','POC95693626')");
        db.execSQL("insert into user_table values(9933254455,'Girish',39456.90,'girishbhai@gmail.com','XXXXXXXXXXXX4563','IND15646848484')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
