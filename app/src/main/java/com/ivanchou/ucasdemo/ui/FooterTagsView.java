package com.ivanchou.ucasdemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ivanchou on 1/20/2015.
 */
public class FooterTagsView extends RelativeLayout implements View.OnClickListener {

    private Context context;
//    private List<String> tags;
    private List<TextView> tagsTV;
    private View rootView;
    private String[] tags = {"足球", "技术", "恋爱", "扯蛋"};

    public FooterTagsView(Context context) {
        super(context);
        TextView tv;
        for (int i = 0; i < tags.length; i++) {
            tv = new TextView(context);
            tv.setText(tags[i]);
            tv.setClickable(true);
            tagsTV.add(tv);
        }
    }

    public FooterTagsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterTagsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FooterTagsView(Context context, List<String> tags) {
        super(context);
        this.context = context;
//        this.tags = tags;
    }

    @Override
    public void onClick(View v) {

    }
}
