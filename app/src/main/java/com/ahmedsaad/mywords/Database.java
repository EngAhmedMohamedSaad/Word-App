package com.ahmedsaad.mywords;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.constraint.solver.state.HelperReference;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME ="WordsApp";
    public static final int DB_VERSION = 1 ;

    public Database(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
