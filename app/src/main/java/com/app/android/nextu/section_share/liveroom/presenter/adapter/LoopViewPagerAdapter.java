package com.app.android.nextu.section_share.liveroom.presenter.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.android.nextu.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * Created by SYSTEM on 2016/7/19.
 */
public class LoopViewPagerAdapter extends PagerAdapter {
    private final Random random = new Random();
    private int mSize;

    public LoopViewPagerAdapter() {
        mSize = 5;
    }

    public LoopViewPagerAdapter(int count) {
        mSize = count;
    }

    @Override public int getCount() {
        return mSize;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override public Object instantiateItem(ViewGroup view, int position) {

        //定义图片

        ImageView imageView = new ImageView(view.getContext());
        Picasso.with(view.getContext()).load(R.drawable.example_1)
                .resize(400,150)
                .error(R.mipmap.ic_launcher).into(imageView);
        view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

    public void addItem() {
        mSize++;
        notifyDataSetChanged();
    }

    public void removeItem() {
        mSize--;
        mSize = mSize < 0 ? 0 : mSize;

        notifyDataSetChanged();
    }
}
