package com.ivanchou.ucasdemo.ui.fragment;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.app.Config;
import com.ivanchou.ucasdemo.core.bean.Event;
import com.ivanchou.ucasdemo.ui.adapter.EventListAdapter;
import com.ivanchou.ucasdemo.ui.view.FooterTagsView;
import com.ivanchou.ucasdemo.ui.view.QuickReturnListView;
import com.ivanchou.ucasdemo.ui.view.QuickReturnListView.DataChangeListener;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanchou on 1/19/2015.
 */
public class TimeLineFragment extends BaseFragment implements OnRefreshListener, DataChangeListener {
    private SwipeRefreshLayout mSwipeLayout;// 下拉刷新
    private QuickReturnListView mListView;
    private List<String> list;// 测试数据
    private List<Event> mEventsList;


    private TranslateAnimation anim;
    private FooterTagsView footerTagsView;
    private int mQuickReturnHeight;
    private ArrayAdapter<String> mListAdapter;
    private EventListAdapter mEvenListAdapter;

    String[] mTags = {"足球", "技术", "恋爱", "扯蛋", "英语", "C++", "Android"};
    int tags = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();
        mEventsList = new ArrayList<Event>();
        mEvenListAdapter = new EventListAdapter(context, mEventsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview_timeline, container, false);
        mListView = (QuickReturnListView) view.findViewById(R.id.lv_maintimeline);
        footerTagsView = (FooterTagsView) view.findViewById(R.id.ftv_footer);
        mListView.setTagsView(footerTagsView, mTags);
        mListView.setDataChangeListener(this);

        // 设置下拉刷新
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        // 设置 ListView 的 adapter
//        mListAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getData());
//        mListView.setAdapter(mListAdapter);
        initEvenListData();
        mListView.setAdapter(mEvenListAdapter);

        return view;
    }


    /**
     * 填充假数据 card list view
     */
    private void initEvenListData() {

        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.startAt = "16:00";
            event.title = "测试一下";
            mEventsList.add(event);
        }
    }

    /**
     * 填充假数据 simple list view
     *
     * @return
     */
    private List<String> getData() {
        list.clear();
        for (int i = 0; i < 14; i++) {
            list.add(i + "--" + mTags[i % 7]);
        }

        if (tags == 0) {
            return list;
        }

        int tmp;
        for (int i = 0; i < mTags.length; i++) {
            tmp = tags;
            if (((tmp >> i) & 1) == 0) {
                for (int j = 0, len = list.size(); j < len; j++) {
                    if (list.get(j).contains(mTags[i])) {
                        list.remove(j);
                        len--;
                        j--;
                    }
                }
            }
        }
        return list;
    }


    @Override
    public void onRefresh() {
        if (Config.MODE.ISDEBUG) {
            Toast.makeText(context, "Refresh start!", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
                if (Config.MODE.ISDEBUG) {
                    Toast.makeText(context, "Refresh stop!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 5000);
    }

    @Override
    public void onLoadMore() {
        Log.e(TAG, "----on load more----");

        // 从网络加载更多的数据

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onRefresh(View v, int position) {
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

    @Override
    public void onLongClickRefresh(View v, int position) {
        Log.e(TAG, "----on long click refresh----");
    }

}
