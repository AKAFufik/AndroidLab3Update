package com.example.mydatabaseupdate;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "StudentDB";
    public static final String TABLE_CONTACTS = "Student";
    public static final String KEY_ID = "_id";
    public static final String F = "F";
    public static final String I = "I";
    public static final String O = "O";
    public static final String dateOfAdd = "date";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key," + F + " text," + O + " text,"+ I + " text,"+ dateOfAdd + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);

    }

}
