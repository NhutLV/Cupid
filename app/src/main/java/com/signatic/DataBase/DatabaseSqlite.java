package com.signatic.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.signatic.model.User;

/**
 * Created by DefaultAccount on 9/7/2016.
 */
public class DatabaseSqlite extends SQLiteOpenHelper {

    private static String Database_Name="CUPID";
    private static String Table_Name="User";
    private static int Version_Database=1;
    public DatabaseSqlite(Context context) {
        super(context, Database_Name, null, Version_Database);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+Table_Name+ " (" +
                "IDUSER INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "USERNAME VARCHAR(255) NOT NULL," +
                "PASSWORD VARCHAR(255) NOT NULL," +
                "EMAIL VARCHAR(255) NOT NULL," +
                "GENDER VARCHAR(255) NOT NULL" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP "+Table_Name+" IF EXISTS");
        onCreate(db);
    }
    public void addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME", u.getUsername());
        values.put("PASSWORD", u.getPassword());
        values.put("GENDER", u.getGender()==1?"Female":"Male");
        values.put("EMAIL",u.getEmail());
        values.put("GENDER", u.getGender());
        db.insert(Table_Name,null,values);
    }
    public void updateUser(int id,String email,String username,String password,int gender,String phonenumber,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME",username);
        values.put("PASSWORD",password);
        values.put("EMAIL",email);
        values.put("GENDER",gender);
        db.update(Table_Name,values,"IDUSER=?",new String[]{String.valueOf(id)});
    }
    public void deteleUser(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Table_Name,"IDUSER=?",new String[]{String.valueOf(id)});
    }
    public User checkLogin(String email,String password){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(Table_Name,new String[]{"USERNAME","PASSWORD","EMAIL","GENDER"},"EMAIL=? AND PASSWORD=?",new String[]{email,password},null,null,null);
        if(cursor.moveToFirst()){

           return new User(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
        }
        return null;
    }

    public User getUsers(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(Table_Name,new String[]{"USERNAME","PASSWORD","EMAIL","GENDER"},"EMAIL=?",new String[]{email},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                return new User(cursor.getString(2),cursor.getString(0),cursor.getString(1),cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return null;
    }

    public User checkEmail(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(Table_Name,new String[]{"USERNAME","PASSWORD","EMAIL","GENDER"},"EMAIL=?",new String[]{email},null,null,null);
        if(cursor.moveToNext()){
            return new User(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
        }
        return null;
    }

    public void resetPass(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("PASSWORD",password);
        db.update(Table_Name,values,"EMAIL=?",new String[]{email});
    }
}
