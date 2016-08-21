package com.app.android.nextu.section_share.selfchannel.presenter.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.liveroom.model.impl_LiveRoom_Model;
import com.app.android.nextu.section_share.selfchannel.model.SelfChannelModel;
import com.app.android.nextu.util.Bag;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SYSTEM on 2016/7/20.
 */
public class RecycleViewAdapter
        extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
        implements View.OnClickListener {


    @Bind(R.id.img_Video2_Poster)
    ImageView imgVideo2Poster;
    @Bind(R.id.txt_Video2_Title)
    TextView txtVideo2Title;
    @Bind(R.id.txt_Video2_Date)
    TextView txtVideo2Date;
    //    private ArrayList<impl_LiveRoom_Model> users;
    private Context mContext;

//    public RecycleViewAdapter(ArrayList<impl_LiveRoom_Model> users) {
//        this.users = users;
//    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
       private Bag<SelfChannelModel> bag;
    private ArrayList<SelfChannelModel> users;

    private  int size ;
    public RecycleViewAdapter(Bag<SelfChannelModel> bag,int size) {
        this.bag = bag;
        this.size = size;
        users = new ArrayList<SelfChannelModel>();
        for(SelfChannelModel model: bag)
        {
            users.add(model);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_video2_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        mContext = viewGroup.getContext();

        return viewHolder;
    }


    //    /添加点击事件
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (impl_LiveRoom_Model) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {



        Picasso.with(mContext).load(users.get(i).getSelfChannel_Poster())
                .resize(size,(int)(size*2/3))
                .into(viewHolder.imgVideo2Poster);

        viewHolder.txtVideo2Title.setText(users.get(i).getSelfChannel_Title());
        viewHolder.txtVideo2Date.setText(users.get(i).getSelfChannel_Date());
//       if(i == 0 )
//       {
//           LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size,
//                   LinearLayout.LayoutParams.WRAP_CONTENT);
//           lp.setMargins(0, 0, 20, 0);
//           viewHolder.imgVideo2Poster.setLayoutParams(lp);
//
//       }


    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    // 更新数据

    public void updateData(ArrayList<impl_LiveRoom_Model> newUsers) {

//        if( users == null)
//            users = new ArrayList<impl_LiveRoom_Model>();
//        users.clear();
//        for(impl_LiveRoom_Model s : newUsers)
//            users.add(s);
//        Log.e("", "-----------------------" );
//        this.notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_Video2_Poster)
        ImageView imgVideo2Poster;
        @Bind(R.id.txt_Video2_Title)
        TextView txtVideo2Title;
        @Bind(R.id.txt_Video2_Date)
        TextView txtVideo2Date;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
