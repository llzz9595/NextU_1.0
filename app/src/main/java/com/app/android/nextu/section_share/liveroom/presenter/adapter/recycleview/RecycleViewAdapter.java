package com.app.android.nextu.section_share.liveroom.presenter.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.liveroom.model.impl_LiveRoom_Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cn.lankton.anyshape.AnyshapeImageView;

/**
 * Created by SYSTEM on 2016/7/20.
 */
public class RecycleViewAdapter
        extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
implements View.OnClickListener
{


    private ArrayList<impl_LiveRoom_Model> users;
    private Context mContext;
    public RecycleViewAdapter(ArrayList<impl_LiveRoom_Model> users)
    {
        this.users = users;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview_liverooms_item, viewGroup, false);
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

        viewHolder.txt_Count.setText(users.get(i).getUser_Count()+"人在看");
        Picasso.with(mContext).load(R.drawable.example_5)
                .resize(200,200)
                .error(R.mipmap.ic_launcher).into(viewHolder.img_Head);
        viewHolder.txt_Name.setText(users.get(i).getUser_Name());
        viewHolder.img_Poster.setImageResource(R.drawable.example_4);
        viewHolder.txt_Title.setText("#"+users.get(i).getUser_Title());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(users.get(i));
    }



    @Override
    public int getItemCount() {
        if(users != null)
        return users.size();
        else
            return 0;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_Count;
        public ImageView img_Head;
        public TextView txt_Name;
        public AnyshapeImageView img_Poster;
        public TextView txt_Title;
        public ImageButton btn_Share;
        public ViewHolder(View view){
            super(view);
           txt_Count = (TextView) view.findViewById(R.id.txt_personsCount);
            img_Head = (ImageView) view.findViewById(R.id.img_Head);
            txt_Name = (TextView) view.findViewById(R.id.txt_Name);
            img_Poster = (AnyshapeImageView) view.findViewById(R.id.img_Poster);
           txt_Title = (TextView) view.findViewById(R.id.txt_Title);
            btn_Share = (ImageButton) view.findViewById(R.id.btn_share);
            img_Poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"进入直播",Toast.LENGTH_SHORT).show();
                }
            });

            btn_Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"分享",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    // 更新数据

    public void updateData(ArrayList<impl_LiveRoom_Model> newUsers) {

        if( users == null)
            users = new ArrayList<impl_LiveRoom_Model>();
        users.clear();
        for(impl_LiveRoom_Model s : newUsers)
            users.add(s);
        Log.e("", "-----------------------" );
        this.notifyDataSetChanged();

    }
}
