package com.example.android.interviewpreparation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Milind Amrutkar on 11-06-2018.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] mThumbsIds = {
            R.mipmap.data_structure_icon, R.mipmap.algorithm_icon, R.mipmap.android_icon, R.mipmap.java_icon
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbsIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(mThumbsIds[i]);
        return imageView;
    }
}
