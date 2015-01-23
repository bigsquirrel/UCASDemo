package com.ivanchou.ucasdemo.core;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ivanchou.ucasdemo.app.Config;

public class EventContentProvider extends ContentProvider {
    protected final String TAG = this.getClass().getSimpleName();
    private EventDAO eventDAO = null;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int EVENT = 1;// 单条消息
    private static final int EVENTS = 2;// 全部消息

    static {
        URI_MATCHER.addURI("com.ivanchou.ucasdemo.EventContentProvider", "event", EVENTS);
        URI_MATCHER.addURI("com.ivanchou.ucasdemo.EventContentProvider", "event/#", EVENT);// 带通配符的用于请求单条消息
    }

    public EventContentProvider() {
    }

    @Override
    public boolean onCreate() {
        eventDAO = new EventDAO(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resultUri = null;
        int flag = URI_MATCHER.match(uri);
        if (flag == EVENTS) {
            long id = eventDAO.insertEvent(values);
            if (Config.MODE.ISDEBUG) {
                Log.e(TAG, "insert ---> id : " + id);
            }
            resultUri = ContentUris.withAppendedId(uri, id);
        }
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = -1;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case EVENT:
                long id = ContentUris.parseId(uri);
                String clause = "id = ?";
                String[] args = { String.valueOf(id) };
                count = eventDAO.deleteEvent(clause, args);
                break;
            case EVENTS:
                count = eventDAO.deleteEvent(selection, selectionArgs);
        }
        if (Config.MODE.ISDEBUG) {
            Log.e(TAG, "delete ---> count : " + count);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = -1;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case EVENT:
                long id = ContentUris.parseId(uri);
                String clause = " id = ?";
                String[] args = { String.valueOf(id) };
                count = eventDAO.updateEvent(values, clause, args);
                break;
            case EVENTS:
                count = eventDAO.updateEvent(values, selection, selectionArgs);
                break;
        }
        if (Config.MODE.ISDEBUG) {
            Log.e(TAG, "update ---> count : " + count);
        }
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case EVENT:
                long id = ContentUris.parseId(uri);
                String clause = " id = ?";
                String[] args = { String.valueOf(id) };
                cursor = eventDAO.queryEvents(clause, args);
                break;
            case EVENTS:
                cursor = eventDAO.queryEvents(selection, selectionArgs);
                break;
        }
        if (Config.MODE.ISDEBUG) {
            Log.e(TAG, "query ---> count : " + cursor.getCount());
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
//        int flag = URI_MATCHER.match(uri);
//        String type = null;
//        switch (flag) {
//            case EVENT:
//                type = "vnd.android.cursor.item/event";
//                break;
//            case EVENTS:
//                type = "vnd.android.cursor.dir/events";
//                break;
//        }
//        return type;
        return null;
    }
}
