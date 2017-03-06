package com.example.waynian.playervsmusic.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.waynian.playervsmusic.base.BasePager;

/**
 * Created by waynian on 2017/3/2.
 */

public class NetVideoPager extends BasePager {
    TextView textView;

    public NetVideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("网络视频");
    }
}
