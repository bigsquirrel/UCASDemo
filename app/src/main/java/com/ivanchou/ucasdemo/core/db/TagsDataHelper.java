package com.ivanchou.ucasdemo.core.db;

import android.content.Context;
import android.net.Uri;

import com.ivanchou.ucasdemo.app.Config;
import com.ivanchou.ucasdemo.core.DataProvider;
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
        return DataProvider.TAGS_CONTENT_URI;
    }

    @Override
    protected String getTableName() {
        return Config.DATABASE.TAGS_TABLE_NAME;
    }

    public TagModel[] query() {
        return new TagModel[0];
    }
}
