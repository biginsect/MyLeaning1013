package com.example.lipeng_ds3.techniquenews.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lipeng_ds3.techniquenews.R;
import com.example.lipeng_ds3.techniquenews.fragment.PictureFragment;
import com.example.lipeng_ds3.techniquenews.fragment.RecyclerViewFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HomeActivity";
    private Button mMenuBtn;
    private Button mPictureBtn;
    private Button mRVBtn;
    private Button mMoreBtn;

    private Fragment pictureFragment;
    private Fragment rvFragment;
    private FragmentManager mManager;
    private Fragment nowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        if (null != getSupportActionBar()){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.home_page_view);
        initView();
        initDefaultFragment();
        //还没有用到
        ImageLoaderConfiguration mConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(mConfiguration);

    }

    private void initDefaultFragment(){
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment_container, pictureFragment);
        transaction.addToBackStack(null);

        transaction.commit();
        nowFragment = pictureFragment;

    }

    private void initView(){
        mMenuBtn = (Button) findViewById(R.id.menu_btn);
        mPictureBtn = (Button) findViewById(R.id.picture_btn);
        mRVBtn = (Button) findViewById(R.id.rv_btn);
        mMoreBtn = (Button) findViewById(R.id.more_btn);
        pictureFragment = new PictureFragment();
        rvFragment = new RecyclerViewFragment();
        mManager = getSupportFragmentManager();

        mMenuBtn.setOnClickListener(this);
        mPictureBtn.setOnClickListener(this);
        mRVBtn.setOnClickListener(this);
        mMoreBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = mManager.beginTransaction();

        switch (v.getId()){
            case R.id.menu_btn:
                Intent i = new Intent(HomeActivity.this, HttpActivity.class);
                startActivity(i);
                Log.d(TAG, "MenuBtn");
                break;
            case R.id.picture_btn:
                if (pictureFragment.isAdded())
                    transaction.hide(nowFragment).show(pictureFragment);
                else {
                    transaction.hide(nowFragment).add(R.id.fragment_container, pictureFragment);
                    transaction.addToBackStack(null);
                }
                nowFragment = pictureFragment;
                transaction.commit();
                Log.d(TAG, "PictureBtn");
                break;
            case R.id.rv_btn:
                if (rvFragment.isAdded())
                    transaction.hide(nowFragment).show(rvFragment);
                else {
                    transaction.hide(nowFragment).add(R.id.fragment_container, rvFragment);
                    transaction.addToBackStack(null);
                }
                nowFragment = rvFragment;
                transaction.commit();
                Log.d(TAG, "JavaBtn");
                break;
            case R.id.more_btn:
                Log.d(TAG, "MoreBtn");
                Intent intent = new Intent(HomeActivity.this,AnimatorActivity.class);
                startActivity(intent);
                break;
        }
    }
}
