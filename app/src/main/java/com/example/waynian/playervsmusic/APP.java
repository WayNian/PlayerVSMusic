package com.example.waynian.playervsmusic;

import android.app.Application;
import android.content.Context;

/**
 * Created by waynian on 2017/2/28.
 */

public class APP extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
