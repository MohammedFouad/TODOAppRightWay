package com.example.mohamed.todoapprightway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohamed on 4/22/2018.
 */

public class MyDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME      = "demoApp.db";
    private static final int DATABASE_VERSION      = 1;
    public static final String COLUMN_ID           = "_id";

    //team table

    public static final String TABLE_TEAM       = "team";
    public static final String COLUMN_NAME   = "name";
    public static final String COLUMN_MASCOT    = "mascot";
    public static final String COLUMN_CITY      = "city";

    public static final String DATABASE_CREATE_TEAM = "create table "
            + TABLE_TEAM + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " string, "
            + COLUMN_MASCOT + " string, "
            + COLUMN_CITY + " string);";




    public MyDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_TEAM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TEAM);

    }
}
