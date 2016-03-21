package com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mygdx.game.database.DatabaseHelper;

/**
 * Created by Radek on 04.02.2016.
 */
public class DbRepository {


    public static SQLiteDatabase getDb(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getWritableDatabase();
    }
}
