package com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.reflect.TypeToken;
import com.mygdx.game.model.NotSendUser;

import java.lang.reflect.Type;
import java.util.List;

import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

/**
 * Created by Radek on 04.02.2016.
 */
public class MultilacDatabaseHelper extends SQLiteOpenHelper {

    static {



        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations()
                .build());

        CupboardFactory.cupboard().register(NotSendUser.class);
    }

    public MultilacDatabaseHelper(Context context) {
        super(context, "multilac.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CupboardFactory.cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        CupboardFactory.cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }
}