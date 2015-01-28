package com.ivanchou.ucasdemo.core.db;

import android.content.Context;
import android.net.Uri;

import com.ivanchou.ucasdemo.core.model.TagModel;

/**
 * Created by ivanchou on 1/27/2015.
 */
public class TagsDataHelper extends BaseDataHelper {
    public TagsDataHelper(Context context) {
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

    public TagModel[] query() {
        return null;
    }
}
