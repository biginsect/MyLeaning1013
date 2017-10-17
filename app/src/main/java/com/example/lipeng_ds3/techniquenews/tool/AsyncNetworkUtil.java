package com.example.lipeng_ds3.techniquenews.tool;


import android.os.Handler;

/**
 * Created by lipeng-ds3 on 2017/10/17.
 * 异步操作访问网络，回调返回数据
 */

public class AsyncNetworkUtil {
    public static void get(final String url, final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetworkUtil.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

    public static void post(final String url, final String content, final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetworkUtil.post(url, content);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

    //回调
    public interface Callback{
        void onResponse(String response);
    }
}
