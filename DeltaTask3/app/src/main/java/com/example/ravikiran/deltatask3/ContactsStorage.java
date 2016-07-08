package com.example.ravikiran.deltatask3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ravikiran on 7/5/2016.
 */
public class ContactsStorage extends SQLiteOpenHelper {

    private static int DATABASE_VERSION=1;
    private static String DATABASE_NAME="contacts.db";
    private static String TABLE_NAME="mycontacts";
    public static String COLUMN_NAME="NAME";
    public static String COLUMN_NUMBER="NUMBER";
    public static String COLUMN_PROFILE="PROFILE";




    public ContactsStorage(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME + " TEXT," +
              COLUMN_NUMBER + " TEXT," +
                COLUMN_PROFILE + " BLOB);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public void addRow(ContactDetails contactDetails){
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,contactDetails.getName());
        values.put(COLUMN_NUMBER,contactDetails.getPh_no());
        values.put(COLUMN_PROFILE,contactDetails.bitmapToByte(contactDetails.getImage()));
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteRow(String name){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }


    public Cursor returnFullTableCursor(){
        Cursor c=null;
        SQLiteDatabase db=getWritableDatabase();
        c=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return c;
    }
}
