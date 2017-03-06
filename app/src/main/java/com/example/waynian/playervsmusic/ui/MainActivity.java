package com.example.waynian.playervsmusic.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.waynian.playervsmusic.R;
import com.example.waynian.playervsmusic.base.BasePager;
import com.example.waynian.playervsmusic.fragment.ReplaceFragment;
import com.example.waynian.playervsmusic.pager.AudioPager;
import com.example.waynian.playervsmusic.pager.NetAudioPager;
import com.example.waynian.playervsmusic.pager.NetVideoPager;
import com.example.waynian.playervsmusic.pager.VideoPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private FrameLayout fl_main_content;

    private RadioGroup rg_bottom_tags;

    private List<BasePager> pagers;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);
        rg_bottom_tags = (RadioGroup) findViewById(R.id.rg_bottom_tags);

        pagers = new ArrayList<>();
        pagers.add(new VideoPager(this));
        pagers.add(new AudioPager(this));
        pagers.add(new NetVideoPager(this));
        pagers.add(new NetAudioPager(this));

        rg_bottom_tags.setOnCheckedChangeListener(new MyOnCheckedListener());
        rg_bottom_tags.check(R.id.rb);
    }

    private class MyOnCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.rb:
                    position = 0;
                    break;
                case R.id.rb1:
                    position = 1;
                    break;
                case R.id.rb2:
                    position = 2;
                    break;
                case R.id.rb3:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            setFragment();
        }
    }

    /**
     * 把页面添加到Fragment中
     */
    private void setFragment() {
        //1.得到FragmentManger
        FragmentManager manager = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = manager.beginTransaction();
        //3.替换
        ft.replace(R.id.fl_main_content, new ReplaceFragment(getBasePager()));
        //4.提交事务
        ft.commit();

    }

    /**
     * 根据位置得到对应的页面
     *
     * @return
     */
    private BasePager getBasePager() {
        BasePager basePager = pagers.get(position);
        if (basePager != null && !basePager.isInitData) {
            basePager.initData();//联网请求或者绑定数据
            basePager.isInitData = true;
        }
        return basePager;
    }
}
