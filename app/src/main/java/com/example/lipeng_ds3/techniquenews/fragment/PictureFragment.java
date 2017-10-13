package com.example.lipeng_ds3.techniquenews.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lipeng_ds3.techniquenews.R;
import com.example.lipeng_ds3.techniquenews.adapter.GridViewAdapter;
import com.example.lipeng_ds3.techniquenews.model.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeng-ds3 on 2017/10/12.
 */

public class PictureFragment extends Fragment {
    private Context mContext;

    private GridView mGridView;
    private GridViewAdapter mAdapter;
    private List<Picture> pictures;
    private boolean isShowDelete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_page, container, false);
        Toast.makeText(getActivity(),"Picture is opened", Toast.LENGTH_SHORT).show();
        mGridView = (GridView)view.findViewById(R.id.home_grid_view);
        initData();
        mAdapter = new GridViewAdapter(getMyActivity(),pictures);
        mGridView.setAdapter(mAdapter);
        initListenerEvent();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initListenerEvent(){
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1)
                    addData();
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShowDelete){
                    isShowDelete = false;
                    mAdapter.setShowDelete(isShowDelete);
                }else {
                    isShowDelete = true;
                    mAdapter.setShowDelete(isShowDelete);
                }

                return false;
            }
        });
    }

    private void initData(){
        pictures = new ArrayList<>();
        Picture picture1 = new Picture(R.drawable.picture_1);
        Picture picture2 = new Picture(R.drawable.picture_2);
        Picture picture3 = new Picture(R.drawable.picture_3);
        Picture picture4 = new Picture(R.drawable.picture_4);
        Picture picture5 = new Picture(R.drawable.picture_5);
        Picture picture6 = new Picture(R.drawable.picture_6);
        pictures.add(picture1);
        pictures.add(picture2);
        pictures.add(picture3);
        pictures.add(picture4);
        pictures.add(picture5);
        pictures.add(picture6);
    }

    private void addData(){
        Picture picture = new Picture(R.drawable.picture_3);
        pictures.add(picture);
        mAdapter.notifyDataSetChanged();
    }

    private Activity getMyActivity(){
        return (Activity)mContext;
    }
}
