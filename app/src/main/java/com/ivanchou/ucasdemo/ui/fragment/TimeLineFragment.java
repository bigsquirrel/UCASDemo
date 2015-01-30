package com.ivanchou.ucasdemo.ui.fragment;

import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.app.Config;
import com.ivanchou.ucasdemo.core.db.EventsDataHelper;
import com.ivanchou.ucasdemo.core.db.TagsDataHelper;
import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.core.model.TagModel;
import com.ivanchou.ucasdemo.ui.adapter.EventCursorAdapter;
import com.ivanchou.ucasdemo.ui.view.FooterTagsView;
import com.ivanchou.ucasdemo.ui.view.QuickReturnListView;
import com.ivanchou.ucasdemo.ui.view.QuickReturnListView.DataChangeListener;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanchou on 1/19/2015.
 */
public class TimeLineFragment extends BaseFragment implements OnRefreshListener, DataChangeListener, LoaderCallbacks<Cursor> {
    private SwipeRefreshLayout mSwipeLayout;// 下拉刷新
    private QuickReturnListView mListView;
    private List<String> list;// 测试数据
    private List<EventModel> mEventsList;


    private FooterTagsView footerTagsView;
    private ArrayAdapter<String> mListAdapter;
    private EventCursorAdapter mEventCursorAdapter;
    private EventsDataHelper mEventsDataHelper;
    private TagsDataHelper mTagsDataHelper;

    private int mPage;
    private boolean mLoadFromCache;


//    String[] mTags = {"足球", "技术", "恋爱", "扯蛋", "英语", "C++", "Android"};
    TagModel[] mTags;
    int tags = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();
        mEventsList = new ArrayList<EventModel>();
        mEventsDataHelper = new EventsDataHelper(context);
        mTagsDataHelper = new TagsDataHelper(context);
        mEventCursorAdapter = new EventCursorAdapter(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview_timeline, container, false);
        mListView = (QuickReturnListView) view.findViewById(R.id.lv_maintimeline);
        footerTagsView = (FooterTagsView) view.findViewById(R.id.ftv_footer);

        // 初始化 tags
        mTags = new TagModel[10];
        TagModel tag = new TagModel();
        for (int i = 0; i < 10; i++) {
            tag.tagId = i;
            tag.tagName = "Android" + i;
            mTags[i] = tag;
        }
        mListView.setTagsView(footerTagsView, mTags);
        mListView.setDataChangeListener(this);
        mListView.setAdapter(mEventCursorAdapter);

        // 设置下拉刷新
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        // 设置 ListView 的 adapter
//        mListAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getData());
//        mListView.setAdapter(mListAdapter);
//        initEvenListData();
//        mListView.setAdapter(mEvenListAdapter);
        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mTagsDataHelper.query().length == 0) {
            // 标签为空则清空所有
//            mEventsDataHelper.empty();
            getTagsData();
        } else {
            mTags =  mTagsDataHelper.query();
            getEventsData();
        }
    }

    /**
     * 从服务器获取标签信息
     */
    public void getTagsData() {
        // 首先获得所有标签信息存入数据库
        // 再获取所有活动 调用 getEventsData
    }

    /**
     * 加载活动
     */
    public void getEventsData() {
        if (mEventsDataHelper.query().length == 0) {
            // 第一次载入若数据库为空，则请求服务器
            mPage = 1;
            getData();
            mLoadFromCache = false;
        } else {
            // 否则加载数据库缓存
            mLoadFromCache = true;
        }
    }

    /**
     * 从服务器获取活动
     */
    public void getData() {
        if (mPage == 1) {
            mLoadFromCache = false;
        }
        // 加载活动

    }


    /**
     * 下拉刷新的回调
     */
    @Override
    public void onRefresh() {
        mPage = 1;
        getData();
//        mSwipeLayout.setRefreshing(true);
    }

    /**
     * 滚送到底部的回调
     */
    @Override
    public void onLoadMore() {
        Log.e(TAG, "----on load more----");

        // 从网络加载更多的数据

    }

    /**
     * 点击标签触发的回调
     * @param v
     * @param position
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onTagClickRefresh(View v, int position) {
        int resId;
        // 生成 tags 字段，取消选中状态
        if (((1 << position) & tags) != 0) {
            resId = R.drawable.tv_unselected_bg;
            tags &= ~(1 << position);
        } else { // 选中状态
            resId = R.drawable.tv_selected_bg;
            tags |= (1 << position);
        }

        Drawable drawable = getResources().getDrawable(resId);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        } else {
            v.setBackgroundDrawable(drawable);
        }

        // 刷新 listview
        getData();
        mListAdapter.notifyDataSetChanged();

        if (Config.MODE.ISDEBUG) {
            Toast.makeText(context, mTags[position] + ", " + Integer.toBinaryString(tags), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 长按标签触发的回调，长按的事件定义为单选
     * @param v
     * @param position
     */
    @Override
    public void onTagLongClickRefresh(View v, int position) {
        Log.e(TAG, "----on long click refresh----");
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.e(TAG, "-------on create loader-----");
        return mEventsDataHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.e(TAG, "-------on load finished-----");

        mEventCursorAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.e(TAG, "-------on loader reset-----");

        mEventCursorAdapter.changeCursor(null);
    }

    /**
     * 填充假数据 card list view
     */
    private void initEvenListData() {

        for (int i = 0; i < 10; i++) {
            EventModel event = new EventModel();
            event.startAt = "16:00";
            event.title = "测试一下";
            mEventsList.add(event);
        }
    }


//    /**
//     * 填充假数据 simple list view
//     *
//     * @return
//     */
//    private List<String> getData() {
//        list.clear();
//        for (int i = 0; i < 14; i++) {
//            list.add(i + "--" + mTags[i % 7]);
//        }
//
//        if (tags == 0) {
//            return list;
//        }
//
//        int tmp;
//        for (int i = 0; i < mTags.length; i++) {
//            tmp = tags;
//            if (((tmp >> i) & 1) == 0) {
//                for (int j = 0, len = list.size(); j < len; j++) {
//                    if (list.get(j).contains(mTags[i])) {
//                        list.remove(j);
//                        len--;
//                        j--;
//                    }
//                }
//            }
//        }
//        return list;
//    }

}
