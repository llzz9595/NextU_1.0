package com.app.android.nextu.userbaseinfo.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.nextu.R;
import com.app.android.nextu.userbaseinfo.model.impl.MySeltChannelModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SYSTEM on 2016/7/30.
 */
public class InBoxListAdapter_MySelfChannel
        extends ArrayAdapter<MySeltChannelModel>
{
    private ArrayList<MySeltChannelModel> mySeltChannels;

    public InBoxListAdapter_MySelfChannel(Context context, int resource,
                                          ArrayList<MySeltChannelModel> objects) {
        super(context, resource, objects);
        this.mySeltChannels = objects;
    }
    @Override
    public int getCount() {

        if(mySeltChannels != null)
        return mySeltChannels.size();
        else
            return 0;
    }

    @Override
    public MySeltChannelModel getItem(int position) {
        return mySeltChannels.get(position) ;
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
        viewHolder.txtMytitle.setText(mySeltChannels.get(position).getSelfChannelTitle());
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        viewHolder.txtMytime.setText(""+mySeltChannels.get(position).getSelfChannelDate());
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
