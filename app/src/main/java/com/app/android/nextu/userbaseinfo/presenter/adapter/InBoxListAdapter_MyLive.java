package com.app.android.nextu.userbaseinfo.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.nextu.R;
import com.app.android.nextu.userbaseinfo.model.impl.MyLiveModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SYSTEM on 2016/7/30.
 */
public class InBoxListAdapter_MyLive extends ArrayAdapter<MyLiveModel> {

    private ArrayList<MyLiveModel> myLives;
    public InBoxListAdapter_MyLive(Context context,
                                   int resource, ArrayList<MyLiveModel> objects) {
        super(context, resource, objects);
        myLives = objects;
    }
    @Override
    public int getCount() {

        if (myLives != null)
        return 10;
        else
            return 0;
    }

    @Override
    public MyLiveModel getItem(int position) {
        return myLives.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ViewHolder viewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.userbase_underview_item, parent, false);
        }

        viewHolder = new ViewHolder(convertView);

        Picasso.with(parent.getContext()).load(R.drawable.example_4)
                .into(viewHolder.imgMyvideo);
        viewHolder.txtMytitle.setText(myLives.get(0).getLiveTitle());
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        viewHolder.txtMytime.setText(""+myLives.get(0).getLiveDate());
        return convertView;
    }



    static class ViewHolder {
        @Bind(R.id.img_myvideo)
        ImageView imgMyvideo;
        @Bind(R.id.txt_mytitle)
        TextView txtMytitle;
        @Bind(R.id.txt_mytime)
        TextView txtMytime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
