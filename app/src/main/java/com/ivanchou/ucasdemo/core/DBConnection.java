package com.ivanchou.ucasdemo.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ivanchou.ucasdemo.app.Config;

/**
 * Created by ivanchou on 1/15/2015.
 */
public class DBConnection {
    private MySQLiteOpenHelper mHelper;

    public DBConnection(Context context) {
        mHelper = new MySQLiteOpenHelper(context, Config.DATABASE.VERSION);
    }

    public SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        return mHelper.getReadableDatabase();
    }

    public void close() {
        mHelper.close();
    }
}
