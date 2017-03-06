package com.example.waynian.playervsmusic.pager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.waynian.playervsmusic.R;
import com.example.waynian.playervsmusic.base.BasePager;
import com.example.waynian.playervsmusic.bean.MediaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waynian on 2017/3/2.
 */

public class VideoPager extends BasePager {

    private static final String TAG = VideoPager.class.getSimpleName();
    private ListView videoLv;

    private TextView tv_no_video;

    private ProgressBar pb_loading;

    private List<MediaItem> list = new ArrayList<>();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (list != null && list.size() > 0) {
                //有数据
                //设置适配器
                //隐藏加载框和文本
                Log.d(TAG, "handleMessage: " + list.toString());
                pb_loading.setVisibility(View.GONE);
                tv_no_video.setVisibility(View.GONE);
            } else {
                //无数据
                tv_no_video.setVisibility(View.VISIBLE);
                pb_loading.setVisibility(View.GONE);
            }
        }
    };

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.video_pager, null);

        videoLv = (ListView) view.findViewById(R.id.video_lv);
        tv_no_video = (TextView) view.findViewById(R.id.tv_no_video);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);

        return view;
    }

    //加载数据
    @Override
    public void initData() {
        super.initData();
        getDataFromLocal();
    }

    /**
     * 从本地SD卡得到数据
     * 1.遍历SD，根据后缀（耗时，基本不用）
     * 2.从provideContent中获取
     * 3.如果是6.系统，需要加上动态权限
     */
    private void getDataFromLocal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isGrantExternalRW((Activity) context);
                ContentResolver provider = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//video在sdcard中的名称
                        MediaStore.Video.Media.DURATION,//video的总时长
                        MediaStore.Video.Media.SIZE,//video的大小
                        MediaStore.Video.Media.DATA,//video的绝对地址
                        MediaStore.Video.Media.ARTIST,//歌曲的作者
                };
                Cursor cursor = provider.query(uri, objs, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(0);
                        Long duration = cursor.getLong(1);
                        Long size = cursor.getLong(2);
                        String data = cursor.getString(3);
                        String artist = cursor.getString(4);
                        MediaItem mediaItem = new MediaItem(name, duration, size, data, artist);
                        list.add(mediaItem);
                    }
                    cursor.close();
                }
                //发消息
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     *
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }

        return true;
    }
}
