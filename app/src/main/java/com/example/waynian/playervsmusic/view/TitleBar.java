package com.example.waynian.playervsmusic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.waynian.playervsmusic.R;

/**
 * Created by waynian on 2017/3/4.
 */

public class TitleBar extends LinearLayout implements View.OnClickListener {
    private View tv_search;
    private View layout_game;
    private View iv_history;

    private Context context;

    /**
     * 在代码实例化该类的时候调用这个方法
     *
     * @param context
     */
    public TitleBar(Context context) {
        this(context, null);
    }

    /**
     * 在布局文件使用该类的时候调用这个方法实例化这个方法
     *
     * @param context
     * @param attrs
     */
    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 当需要样式的时候
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_search = getChildAt(1);
        layout_game = getChildAt(2);
        iv_history = getChildAt(3);

        tv_search.setOnClickListener(this);
        layout_game.setOnClickListener(this);
        iv_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_game:
                Toast.makeText(context, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_history:
                Toast.makeText(context, "历史", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
