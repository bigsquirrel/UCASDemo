package com.ivanchou.ucasdemo.ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.ui.view.FooterTagsView;
import com.ivanchou.ucasdemo.ui.view.QuickReturnListView;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanchou on 1/19/2015.
 */
public class TimeLineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;
    private QuickReturnListView mListView;
    private List<String> list;// 测试数据


    private static final int STATE_ONSCREEN = 0;
    private static final int STATE_OFFSCREEN = 1;
    private static final int STATE_RETURNING = 2;
    private int mState = STATE_ONSCREEN;
    private int mScrollY;
    private int mMinRawY = 0;

    private TranslateAnimation anim;
    private FooterTagsView mQuickReturnView;
    private int mQuickReturnHeight;
    private ArrayAdapter<String> mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_maintimeline, container, false);
        mListView = (QuickReturnListView) view.findViewById(R.id.lv_maintimeline);
        mQuickReturnView = (FooterTagsView) view.findViewById(R.id.footer);
        mListAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getData());
        mListView.setAdapter(mListAdapter);

        mListView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mQuickReturnHeight = mQuickReturnView.getHeight();
                        mListView.computeScrollY();
                    }
                });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @SuppressLint("NewApi")
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                mScrollY = 0;
                int translationY = 0;

                if (mListView.scrollYIsComputed()) {
                    mScrollY = mListView.getComputedScrollY();
                }

                int rawY = mScrollY;

                switch (mState) {
                    case STATE_OFFSCREEN:
                        if (rawY >= mMinRawY) {
                            mMinRawY = rawY;
                        } else {
                            mState = STATE_RETURNING;
                        }
                        translationY = rawY;
                        break;

                    case STATE_ONSCREEN:
                        if (rawY > mQuickReturnHeight) {
                            mState = STATE_OFFSCREEN;
                            mMinRawY = rawY;
                        }
                        translationY = rawY;
                        break;

                    case STATE_RETURNING:

                        translationY = (rawY - mMinRawY) + mQuickReturnHeight;

                        System.out.println(translationY);
                        if (translationY < 0) {
                            translationY = 0;
                            mMinRawY = rawY + mQuickReturnHeight;
                        }

                        if (rawY == 0) {
                            mState = STATE_ONSCREEN;
                            translationY = 0;
                        }

                        if (translationY > mQuickReturnHeight) {
                            mState = STATE_OFFSCREEN;
                            mMinRawY = rawY;
                        }
                        break;
                }

                /** this can be used if the build is below honeycomb **/
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
                    anim = new TranslateAnimation(0, 0, translationY,
                            translationY);
                    anim.setFillAfter(true);
                    anim.setDuration(0);
                    mQuickReturnView.startAnimation(anim);
                } else {
                    mQuickReturnView.setTranslationY(translationY);
                }

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });


        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        initTagsView();
        return view;
    }

    private void initViews(View view) {

    }

    /**
     * 填充假数据
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
            System.out.println(i + ", ------------>" + Integer.toBinaryString(tmp >> i));
            if (((tmp >> i) & 1) == 0) {
                for (int j = 0, len = list.size(); j < len; j++) {
                    System.out.println("i--> " + i + ", j-->" + j);
                    if (list.get(j).contains(mTags[i])) {
                        System.out.println("rm -->" + list.get(j));
                        list.remove(j);
                        len--;
                        j--;
                    }
                }
            }
        }
        return list;
    }

    String[] mTags = {"足球", "技术", "恋爱", "扯蛋", "英语", "C++", "Android"};
    int tags = 0;

    /**
     * 填充 tag view
     */
    private void initTagsView() {
        LayoutInflater mInflater = LayoutInflater.from(context);
        for (int i = 0; i < mTags.length; i++) {
            final int tmp = i;
            TextView tv = (TextView) mInflater.inflate(R.layout.textview_tags, mQuickReturnView, false);
            tv.setText(mTags[i]);
            tv.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    int resId;
                    // 生成 tags 字段，取消选中状态
                    if (((1 << tmp) & tags) != 0) {
                        resId = R.drawable.tv_unselected_bg;
                        tags &= ~(1 << tmp);
                    } else { // 选中状态
                        resId = R.drawable.tv_selected_bg;
                        tags |= (1 << tmp);
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
                    Toast.makeText(context, mTags[tmp] + ", " + Integer.toBinaryString(tags), Toast.LENGTH_SHORT).show();
                }
            });
            mQuickReturnView.addView(tv);
        }
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
