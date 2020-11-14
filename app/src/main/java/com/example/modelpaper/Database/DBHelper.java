package com.example.modelpaper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME="userInfo1.db";
    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE=
                " CREATE TABLE " + UserProfile.Users.TABLE_NAME+ " ("+
                        UserProfile.Users._ID+" INTEGER PRIMARY KEY," +
                        UserProfile.Users.COLUMN_NAME_USERNAME + " TEXT,"+
                        UserProfile.Users.COLUMN_NAME_DOB + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_PASSWORD+ " TEXT," +
                        UserProfile.Users.COLUMN_NAME_GENDER+" TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addInfor(String username,String password){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_USERNAME,username);
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD,password);
        long val=db.insert(UserProfile.Users.TABLE_NAME,null,values);
        return  val;
    }

    public ArrayList readAllInfor(String req){
SQLiteDatabase db=getReadableDatabase();
String [] columns={
        UserProfile.Users._ID,
        UserProfile.Users.COLUMN_NAME_USERNAME,
        UserProfile.Users.COLUMN_NAME_DOB,
        UserProfile.Users.COLUMN_NAME_PASSWORD,
        UserProfile.Users.COLUMN_NAME_GENDER
};

String sortBy= UserProfile.Users._ID+" DESC";

        Cursor cursor=db.query(
                UserProfile.Users.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortBy
        );

        ArrayList usernames=new ArrayList();
        ArrayList dobs=new ArrayList();
        ArrayList passwords=new ArrayList();
        ArrayList genders=new ArrayList();

        while(cursor.moveToNext()){
            String username=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String dob=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DOB));
            String password=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));
            String gender=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));

            usernames.add(username);
            dobs.add(dob);
            passwords.add(password);
            genders.add(gender);
        }

        cursor.close();

        if(req=="username"){
            return usernames;
        }
        if(req=="dob"){
            return  dobs;
        }
        if(req=="password"){
            return  passwords;
        }
        else{
            return  null;
        }


    }


    public ArrayList readAllInfor(String username,String req){
        SQLiteDatabase db=getReadableDatabase();
        String [] columns={
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_DOB,
                UserProfile.Users.COLUMN_NAME_PASSWORD,
                UserProfile.Users.COLUMN_NAME_GENDER
        };

        String sortBy= UserProfile.Users._ID+" DESC";
        Cursor cursor=db.query(
                UserProfile.Users.TABLE_NAME,
                columns,
                UserProfile.Users.COLUMN_NAME_USERNAME+ " =?",
                new String []{ username},
                null,
                null,
                sortBy
        );

        ArrayList usernames=new ArrayList();
        ArrayList dobs=new ArrayList();
        ArrayList passwords=new ArrayList();
        ArrayList genders=new ArrayList();


        while(cursor.moveToNext()){
            String uname=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String dob=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DOB));
            String password=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));
            String gender=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));

            usernames.add(uname);
            dobs.add(dob);
            passwords.add(password);
            genders.add(gender);
        }

        cursor.close();

        if(req=="username"){
            return usernames;
        }
        if(req=="dob"){
            return  dobs;
        }
        if(req=="password"){
            return  passwords;
        }
        if(req=="gender"){
            return  genders;
        }
        else{
            return  null;
        }
    }

    public long deleteInfor(String username){
        SQLiteDatabase db=getReadableDatabase();
        String selection= UserProfile.Users.COLUMN_NAME_USERNAME+" =?";
        String [] selectionArgs={ username };
       long val= db.delete(UserProfile.Users.TABLE_NAME,selection,selectionArgs);
       return val;
    }

    public long updatInfo(String username,String dob,String password,String gender){
        SQLiteDatabase db=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_DOB,dob);
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD,password);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER,gender);
        String selection= UserProfile.Users.COLUMN_NAME_USERNAME+" =?";
        String [] selectionArgs={ username };
        long val=db.update(UserProfile.Users.TABLE_NAME,values,selection,selectionArgs);
        return  val;
    }
}
