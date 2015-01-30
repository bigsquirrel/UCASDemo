package com.ivanchou.ucasdemo.core.db;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;

import com.ivanchou.ucasdemo.app.Config;
import com.ivanchou.ucasdemo.core.DataProvider;
import com.ivanchou.ucasdemo.core.model.TagModel;

import java.util.ArrayList;

/**
 * Created by ivanchou on 1/27/2015.
 */
public class TagsDataHelper extends BaseDataHelper {
    private Context mContext;

    public TagsDataHelper(Context context) {
        super(context);
        mContext = context;
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
        TagModel[] tags;
        Cursor cursor = query(null, null, null, null);
        if (cursor.moveToFirst()) {
            ArrayList<TagModel> tagModelArrayList = new ArrayList<TagModel>();
            do {
                tagModelArrayList.add(TagModel.fromCursor(mContext, cursor));
            } while (cursor.moveToNext());
            tags = tagModelArrayList.toArray(new TagModel[tagModelArrayList.size()]);
        } else {
            tags = new TagModel[0];
        }
        cursor.close();
        return tags;
    }
}
