package com.app.android.nextu.section_share.reviewroom.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.liveroom.http.BaseUrl;
import com.app.android.nextu.section_share.reviewroom.model.Impl_ReviewRoom_Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SYSTEM on 2016/8/4.
 */
public class Review_RecyclerAdapter extends
        RecyclerView.Adapter<Review_RecyclerAdapter.ViewHolder>
        implements View.OnClickListener {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private ArrayList<Impl_ReviewRoom_Model> models;
    private int size;
    private String baseUrl = BaseUrl.baseUrlForNginx;
    public Review_RecyclerAdapter(ArrayList<Impl_ReviewRoom_Model> models,int size) {
        this.models = models;

        this.size= size;
    }

    private Context mContext;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_video3_item, parent, false);
        mContext = view.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(mContext).load(baseUrl+models.get(position).getReviewRoom_Poster())
                .resize(size,(int)(size*2/3))
                .into(holder.imgVideo3Poster);
        holder.txtVideo3Title
                .setText(models.get(position).getReviewRoom_Title());
        holder.txtVideo3Date
                .setText(models.get(position).getReviewRoom_Date());
        holder.txtVideo3Type
                .setText("#"+models.get(position).getReviewRoom_Type());
        holder.txtVideo3Comments
                .setText(models.get(position).getReviewRoom_Comments()+"条评论");
    }



    @Override
    public int getItemCount() {
        if(models != null)

        return models.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {

    }

    static class ViewHolder  extends RecyclerView.ViewHolder {
        @Bind(R.id.img_Video3_Poster)
        ImageView imgVideo3Poster;
        @Bind(R.id.txt_Video3_Title)
        TextView txtVideo3Title;
        @Bind(R.id.txt_Video3_Date)
        TextView txtVideo3Date;
        @Bind(R.id.txt_Video3_Type)
        TextView txtVideo3Type;
        @Bind(R.id.txt_Video3_Comments)
        TextView txtVideo3Comments;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
