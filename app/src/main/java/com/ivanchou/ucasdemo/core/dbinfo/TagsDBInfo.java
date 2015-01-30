package com.ivanchou.ucasdemo.core.dbinfo;

import com.ivanchou.ucasdemo.app.Config;
import com.ivanchou.ucasdemo.core.db.Column.DataType;
import com.ivanchou.ucasdemo.core.db.SQLiteTable;

/**
 * tag 表的定义
 * Created by ivanchou on 1/28/2015.
 */
public class TagsDBInfo extends BaseTagsDBInfo{

    public static final SQLiteTable TABLE = new SQLiteTable(Config.DATABASE.TAGS_TABLE_NAME)
           .addColumn(TAG_ID, DataType.INTEGER)
           .addColumn(TAG_NAME, DataType.TEXT)
           .addColumn(IS_INTERESTED, DataType.INTEGER);
}
