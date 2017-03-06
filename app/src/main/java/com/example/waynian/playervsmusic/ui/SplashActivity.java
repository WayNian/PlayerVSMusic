package com.example.waynian.playervsmusic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.waynian.playervsmusic.R;

public class SplashActivity extends AppCompatActivity {

    private boolean isMain = false;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 2000);
    }

    /**
     * 保证APP不会返回多个主页面
     * 1.MainActivity要设置为android:launchMode="singleTask
     * 2.设置isMain
     */
    private void startMainActivity() {
        if (!isMain) {
            isMain = true;
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent(event);
    }

    /**
     *
     * 防止启动时点击返回键到桌面 还是进入APP
     *  mHandler.removeCallbacksAndMessages(null);
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

    }
}
