package com.mygdx.game.model;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Radek on 2016-02-26.
 */
public class Row implements Serializable{
    public long id;
    public String nazwa_apteki;
    public String ulica;
    public String miasto;
    public String wojewodztwo;
    public String nazwisko_przedstawiciela;
    public String imie_przedstawiciela;
    public String rks_nazwisko;
    public String rks_imie;

    protected Long _id;

    public void save(SQLiteDatabase db) {
        _id = cupboard().withDatabase(db).put(this);
    }

    public void delete(SQLiteDatabase db) {
        cupboard().withDatabase(db).delete(this);
    }



}
