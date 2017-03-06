package com.example.waynian.playervsmusic.base;

import android.content.Context;
import android.view.View;

/**
 * Created by waynian on 2017/3/2.
 */

public abstract class BasePager {

    public final Context context;

    public View rootView;
    public boolean isInitData;

    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    /**
     * 强制子类实现
     * @return
     */
    public abstract View initView();

    public void initData(){}
}
