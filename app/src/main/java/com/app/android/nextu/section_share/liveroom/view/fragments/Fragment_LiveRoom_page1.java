package com.app.android.nextu.section_share.liveroom.view.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.liveroom.model.impl_LiveRoom_Model;
import com.app.android.nextu.section_share.liveroom.presenter.ILiveRoom_Presenter;
import com.app.android.nextu.section_share.liveroom.presenter.adapter.LoopViewPagerAdapter;
import com.app.android.nextu.section_share.liveroom.presenter.adapter.recycleview.RecycleViewAdapter;
import com.app.android.nextu.section_share.liveroom.presenter.impl.Impl_LiveRoom_Presenter;
import com.app.android.nextu.section_share.liveroom.view.ILiveRoom_View;
import com.app.android.nextu.widget.RecycleViewDivider;
import com.app.android.nextu.widget.loopview.LoopViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;


/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment_LiveRoom_page1 extends Fragment implements ILiveRoom_View {

    @Bind(R.id.loopViewPager)
    LoopViewPager loopViewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    @Bind(R.id.btn_getAllLive)
    Button btnGetAllLive;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.myScrollView)
    ScrollView myScrollView;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;

    private ImageButton btn_share;
    private RecycleViewAdapter recycleViewAdapter;
    private ArrayList<impl_LiveRoom_Model> users;
    private ILiveRoom_Presenter liveRoom_presenter;
    private Handler handler;
    public Fragment_LiveRoom_page1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_liveroom_1page, container, false);
        ButterKnife.bind(this, view);
//        myScrollView.smoothScrollTo(0, 0);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        loopViewPager.setAdapter(new LoopViewPagerAdapter());
        indicator.setViewPager(loopViewPager);

        liveRoom_presenter = new Impl_LiveRoom_Presenter(this);
        //得到当前所有的房间

        myScrollView.smoothScrollTo(0, 0);
        liveRoom_presenter.getRoomsData();

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        handler = new Handler(Looper.getMainLooper());
        swipeRefreshWidget.setProgressBackgroundColor(android.R.color.white);
        swipeRefreshWidget.setColorSchemeResources(
                R.color.green

               );
        swipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                liveRoom_presenter.getReflash();
                swipeRefreshWidget.setRefreshing(false);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // 刷新 数据
    @Override
    public void init(ArrayList<impl_LiveRoom_Model> users) {
        if(this.getContext()!= null){
        recycleViewAdapter = new RecycleViewAdapter(users);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),
                RecycleViewDivider.VERTICAL_LIST));

        Log.e("","init");}
    }

    @Override
    public void reflash(ArrayList<impl_LiveRoom_Model> users) {
          recycleViewAdapter.updateData(users);
    }



}
