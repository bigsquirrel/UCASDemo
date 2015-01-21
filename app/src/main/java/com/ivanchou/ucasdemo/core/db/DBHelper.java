package com.ivanchou.ucasdemo.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ivanchou.ucasdemo.app.Config;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static DBHelper mDBHelper;

    /** 创建表的 SQL 语句 */
    private static final String TIMELINE_TABLE_CREATE = "";

    public DBHelper(Context context) {
        super(context, Config.DATABASE.NAME, null, Config.DATABASE.VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (mDBHelper == null) {
            mDBHelper = new DBHelper(context);
        }
        return mDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TIMELINE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS event");

    }


    /*----------------------------------- 数据库的 CRUD 操作 ---------------------------------------*/

    public long addRecords(String table, ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.insert(table, "", cv);
    }

    public Cursor getAllRecords(String table, String orderBy) {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.query(table, null, null, null, null, null, orderBy);
    }

    public long deleteAllRecords(String table, String whereClause, String whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(table, whereClause + "=?", new String[] {whereArgs});
    }

    public void updateRecord(String table, ContentValues cv, String whereClause, String whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.update(table, cv, whereClause + "=?", new String[] {whereArgs});
    }

}
