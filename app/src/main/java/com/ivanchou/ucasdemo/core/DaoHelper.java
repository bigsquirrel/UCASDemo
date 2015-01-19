package com.ivanchou.ucasdemo.core;

import android.content.Context;

import com.ivanchou.ucasdemo.app.Config;

import java.util.List;

/**
 * Created by ivanchou on 1/15/2015.
 */
public class DaoHelper<T> {
    protected final String TAG = this.getClass().getSimpleName();
    private static MySQLiteOpenHelper db;

    @SuppressWarnings("unchecked")
    public DaoHelper(Context context, Class<T> clazz) {
        db = new MySQLiteOpenHelper(context, Config.DATABASE.VERSION);

    }

    // ===================新增记录开始===================
    /** 新增一条记录 */
    public boolean create(T entity) {

        return false;
    }

    /** 新增一条记录 */
    public boolean createOrUpdate(T entity) {
        return false;

    }

    /** 新增多条记录 */
    public boolean create(List<T> entities) {

        return false;

    }

    /** 新增多条记录 */
    public boolean createOrUpdate(List<T> entities) {

        return false;

    }

    // ===================新增记录结束===================

    // ===================删除记录开始===================
    /** 删除一条记录 */
    public boolean delete(T entity) {
        return false;

    }

    /** 删除多条记录 */
    public boolean delete(List<T> entities) {
        return false;

    }

    /** 删除所有记录 */
    public boolean deleteAll() {
        return false;

    }

    /** 通过id删除一条记录 */
    public boolean deleteById(Long id) {
        return false;


    }

    /** 通过多个id删除多条记录 */
    public boolean deleteIds(List<Long> ids) {
        return false;

    }

    // ===================删除记录结束===================

    // ===================修改记录开始===================
    /** 修改一条记录 */
    public boolean update(T entity) {
        return false;

    }

    /** 修改多条记录 */
    public boolean update(List<T> entities) {
        return false;

    }

    /** 通过id修改记录 */
    public boolean updateId(T entity, Long id) {
        return false;

    }

    // ===================修改记录结束===================

    // ===================查询记录开始===================
//    /** 查询所有记录 */
//    public List<T> queryForAll() {
//
//    }
//
//    /** id查询一条记录 */
//    public T queryForId(Long id) {
//
//    }
//
//    /** 查询多条记录 */
//    public List<T> queryForEq(String fieldName, String value) {
//
//    }
//
//    public List<T> query(PreparedQuery<T> preparedQuery) {
//
//    }
//
//    /** 分页查询记录 */
//    public List<T> queryPaging(long page, long pageSize) {
//
//    }
//
//    public List<T> getQueryList(long start, long end, String content1,
//                                String content2, String content3, String flag) {
//
//    }
//
//    public List<T> getQueryList(long start, long end, String content1,
//                                String content2, String flag) {
//
//    }
//
//    public List<T> getQueryList(long start, long end, String content,
//                                String flag) {
//
//    }

    // ===================查询记录结束===================

    // =================自定义SQL执行语句开始=================

    public boolean executeRawNoArgs(String sql) {
        return false;

    }

    public boolean executeRaw(String sql, String... str) {
        return false;

    }

    // =================自定义SQL执行语句结束=================
}
