package com.app.android.nextu.section_share.liveroom.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.android.nextu.section_share.liveroom.view.fragments.Fragment_LiveRoom_page1;
import com.app.android.nextu.section_share.reviewroom.view.fragment.Fragment_VideoRoom_page3;
import com.app.android.nextu.section_share.selfchannel.view.Fragment_VideoRoom_page2;

/**
 *  滑动标题栏适配器
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final String page1 ="正在直播";
    private final String page2 ="自频道";
    private final String page3= "往期回顾";

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public Fragment getItem(int num)
    {
        switch (num)
        {
            case 0 :return new Fragment_LiveRoom_page1();
            case 1: return new Fragment_VideoRoom_page2();
            case 2: return new Fragment_VideoRoom_page3();

        }
           return  null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0: return page1;
            case 1: return page2;
            case 2: return page3 ;
        }
        return null;
    }

}