package com.ivanchou.ucasdemo.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ivanchou.ucasdemo.app.Config;

/**
 * Created by ivanchou on 1/15/2015.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public MySQLiteOpenHelper(Context context, int version) {
        super(context, Config.DATABASE.NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
