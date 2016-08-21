package com.app.android.nextu.widget;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.android.nextu.R;

import it.neokree.materialtabs.MaterialTab;

/**
 * Created by SYSTEM on 2016/8/2.
 */
public class MyTab extends MaterialTab {

    private TextView tv;
    public MyTab(Context ctx, boolean hasIcon) {
        super(ctx, hasIcon);
       View completeView = LayoutInflater.from(ctx).inflate(R.layout.tab, null);

        tv = (TextView) completeView.findViewById(R.id.text);

    }

    public void setTv(int size) {
        this.tv.setText("test");
       this. tv.setTextSize( TypedValue.COMPLEX_UNIT_DIP,size);
    }
}
