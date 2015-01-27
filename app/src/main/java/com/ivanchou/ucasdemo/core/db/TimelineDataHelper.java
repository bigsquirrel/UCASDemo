package com.ivanchou.ucasdemo.core.db;

import android.content.Context;
import android.net.Uri;

/**
 * Created by ivanchou on 1/27/2015.
 */
public class TimelineDataHelper extends BaseDataHelper {
    public TimelineDataHelper(Context context) {
        super(context);
    }

    @Override
    protected Uri getContentUri() {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }
}
