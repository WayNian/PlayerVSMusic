package com.example.waynian.playervsmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waynian.playervsmusic.base.BasePager;

/**
 * Created by waynian on 2017/3/3.
 * Fragment不能用匿名内部类的方式  否则会报Fragment null must be a public static class
 */

public class ReplaceFragment extends Fragment {
    private BasePager basePager;


    public ReplaceFragment(BasePager basePager) {
        this.basePager = basePager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (basePager != null) {
            //各个页面的视图
            return basePager.rootView;
        }
        return null;

    }
}

