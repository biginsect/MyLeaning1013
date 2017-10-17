package com.example.lipeng_ds3.techniquenews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lipeng_ds3.techniquenews.R;
import com.example.lipeng_ds3.techniquenews.tool.AsyncNetworkUtil;

/**
 * Created by lipeng-ds3 on 2017/10/17.
 */

public class HttpActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_activity);

        mTextView = (TextView)findViewById(R.id.http_tv);
        AsyncNetworkUtil.get("https://www.baidu.com", new AsyncNetworkUtil.Callback() {
            @Override
            public void onResponse(String response) {
                mTextView.setText(response);
            }
        });
    }
}
