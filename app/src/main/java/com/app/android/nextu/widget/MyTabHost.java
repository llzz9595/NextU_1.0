package com.app.android.nextu.widget;

import android.content.Context;
import android.util.AttributeSet;

import java.util.LinkedList;
import java.util.List;

import it.neokree.materialtabs.MaterialTabHost;

/**
 * Created by SYSTEM on 2016/8/2.
 */
public class MyTabHost extends MaterialTabHost {
    private List<MyTab> tabs;

    public MyTabHost(Context context) {
        super(context);
    }

    public MyTabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tabs = new LinkedList<MyTab>();
    }

    public MyTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setText(int size ,List<MyTab> tabs)
    {
           for(MyTab tab :   tabs)
               tab.setTv(5);
    }
}
