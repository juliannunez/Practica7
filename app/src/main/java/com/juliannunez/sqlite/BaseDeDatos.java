package com.juliannunez.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 02/05/2016.
 */
public class BaseDeDatos extends SQLiteOpenHelper {
    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table estudiantes(identificacion integer primary key ,nombre text ,cantidad integer ,precio integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists estudiantes");
        db.execSQL("create table estudiantes(identificacion integer primary key ,nombre text ,cantidad integer ,precio integer)");
    }
}
