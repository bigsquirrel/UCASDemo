package com.ivanchou.ucasdemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.ui.QuickReturnListView;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanchou on 1/19/2015.
 */
public class TimeLineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeLayout;
    private QuickReturnListView mListView;
    private List<String> list;// 测试数据


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_maintimeline, container, false);
        mListView = (QuickReturnListView) view.findViewById(R.id.lv_maintimeline);
        mListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
                getData()));

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return view;
    }

    /**
     * 填充假数据
     * @return
     */
    private List<String> getData() {
        list.add("Hello");
        list.add("I am ivanchou");
        list.add("An Android Developer");
        list.add("Love Open Source");
        list.add("And this is fake data");
        list.add("For test listview");
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        return list;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(context, "Refresh start!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
                Toast.makeText(context, "Refresh stop!", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
    }
}
