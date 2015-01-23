package com.ivanchou.ucasdemo.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ivanchou.ucasdemo.app.Config;
import com.ivanchou.ucasdemo.core.db.DBHelper;

/**
 * Created by ivanchou on 1/23/2015.
 */
public class EventDAO {
    private DBHelper helper = null;
    private static final String EVENT_TABLE_NAME = Config.DATABASE.EVENT_TABLE_NAME;

    public EventDAO(Context context) {
        helper = new DBHelper(context);
    }

    public long insertEvent(ContentValues values) {
        long id = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            id = database.insert(EVENT_TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return id;
    }

    public int deleteEvent(String whereClause, String[] whereArgs) {
        int count = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            count = database.delete(EVENT_TABLE_NAME, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return count;
    }

    public int updateEvent(ContentValues values, String whereClause,
                           String[] whereArgs) {
        SQLiteDatabase database = null;
        int count = -1;
        try {
            database = helper.getWritableDatabase();
            count = database.update(EVENT_TABLE_NAME, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != database) {
                database.close();
            }
        }
        return count;
    }

    public Cursor queryEvents(String selection, String[] selectionArgs) {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = helper.getReadableDatabase();
            cursor = database.query(true, EVENT_TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != database) {
//				database.close();
            }
        }
        return cursor;
    }
}
