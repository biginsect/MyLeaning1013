package com.example.lipeng_ds3.techniquenews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.lipeng_ds3.techniquenews.R;
import com.example.lipeng_ds3.techniquenews.model.Picture;

import java.util.List;

/**
 * Created by lipeng-ds3 on 2017/10/12.
 */

public class GridViewAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Picture> pictures;
    private boolean isShowDelete;

    public GridViewAdapter(Context context, List<Picture> pictureList){
        this.mContext = context;
        this.pictures = pictureList;
    }
    @Override
    public int getCount() {
        return (pictures.size() + 1);
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (null == convertView){
            view = LayoutInflater.from(mContext).inflate(R.layout.home_grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)view.findViewById(R.id.iv_head);
            viewHolder.deleteImageView = (ImageView)view.findViewById(R.id.delete_iv);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        if (position < pictures.size()){
            Picture picture = (Picture)getItem(position);
            viewHolder.imageView.setImageResource(picture.getImageId());
            viewHolder.deleteImageView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);
            //delete图片相当于一个button，点击触发相应的事件，对当前picture删除
            if (isShowDelete){
                viewHolder.deleteImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pictures.remove(position);
                        setShowDelete(false);
                    }
                });
            }

        }else {
            viewHolder.imageView.setImageResource(R.mipmap.add);
            viewHolder.deleteImageView.setImageResource(R.mipmap.delete);
        }

        return view;
    }

    class ViewHolder{
        ImageView imageView, deleteImageView;
    }

    public void setShowDelete(boolean isShowDelete){
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }
}
