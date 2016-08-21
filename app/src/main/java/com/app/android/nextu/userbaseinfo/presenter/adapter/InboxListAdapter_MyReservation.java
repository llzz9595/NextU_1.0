package com.app.android.nextu.userbaseinfo.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.nextu.R;
import com.app.android.nextu.userbaseinfo.model.impl.MyReservationModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SYSTEM on 2016/7/30.
 */
public class InboxListAdapter_MyReservation
        extends ArrayAdapter<MyReservationModel> {


    private ArrayList<MyReservationModel> myReservations;
    public InboxListAdapter_MyReservation(Context context, int resource,
                                          ArrayList<MyReservationModel> objects) {
        super(context, resource, objects);
        this.myReservations = objects;
    }

    @Override
    public int getCount()

    {
        if(myReservations != null)
        return myReservations.size();
        else
            return 0;
    }

    @Override
    public MyReservationModel getItem(int position) {
        return myReservations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.userbase_underview_item, parent, false);
        }


        ImageView imgMyvideo = (ImageView) convertView.findViewById(R.id.img_myvideo);
        TextView txtMytime = (TextView) convertView.findViewById(R.id.txt_mytime);
        TextView txtMytitle = (TextView) convertView.findViewById(R.id.txt_mytitle);
        imgMyvideo.setImageResource(R.drawable.example_4);
        txtMytitle.setText("我的收藏我的收藏");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        txtMytime.setText(""+format.format(date));
        return convertView;
    }
}
