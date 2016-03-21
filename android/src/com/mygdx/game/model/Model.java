package com.mygdx.game.model;

import android.content.Context;

import com.DbRepository;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public abstract class Model implements Serializable {

    @SerializedName("id")
    protected Long id;

    private Long _id;

    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        this.id = _id;
    }

    public void save(Context context) {
        _id = id;
        cupboard().withDatabase(DbRepository.getDb(context)).put(this);
        /// = cupboard().withDatabase(DbRepository.getDb()).put(this);
    }

    public void remove(Context context) {
        _id = id;
        cupboard().withDatabase(DbRepository.getDb(context)).delete(this);
        /// = cupboard().withDatabase(DbRepository.getDb()).put(this);
    }

    public void delete(Context context) {
       // _id = id;
        cupboard().withDatabase(DbRepository.getDb(context)).delete(this);
    }
}
