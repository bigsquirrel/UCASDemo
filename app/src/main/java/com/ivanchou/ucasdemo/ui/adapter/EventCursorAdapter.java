package com.ivanchou.ucasdemo.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by ivanchou on 1/23/2015.
 */
public class EventCursorAdapter extends CursorAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public EventCursorAdapter(Context context, Cursor c) {
        super(context, c);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
