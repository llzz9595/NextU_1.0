package com.app.android.nextu.section_share.reviewroom.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.liveroom.presenter.adapter.LoopViewPagerAdapter;
import com.app.android.nextu.section_share.reviewroom.presenter.IReviewRoom_Presenter;
import com.app.android.nextu.section_share.reviewroom.presenter.Impl_ReviewRoom_Presenter;
import com.app.android.nextu.section_share.reviewroom.presenter.adapter.Review_RecyclerAdapter;
import com.app.android.nextu.section_share.reviewroom.view.IReviewRoom_View;
import com.app.android.nextu.widget.loopview.LoopViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by SYSTEM on 2016/7/19.
 */
public class Fragment_VideoRoom_page3 extends Fragment implements IReviewRoom_View {

    @Bind(R.id.loopViewPager_3)
    LoopViewPager loopViewPager3;
    @Bind(R.id.indicator_3)
    CircleIndicator indicator3;
    @Bind(R.id.recyclerview_recommed)
    RecyclerView recyclerviewRecommed;
    @Bind(R.id.recyclerview_lastest)
    RecyclerView recyclerviewLastest;

    private IReviewRoom_Presenter presenter;

    public Fragment_VideoRoom_page3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_video_3page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loopViewPager3.setAdapter(new LoopViewPagerAdapter());
        indicator3.setViewPager(loopViewPager3);

        presenter = new Impl_ReviewRoom_Presenter(this);
        presenter.getPopular();
        presenter.getLatest();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getPopularReview(ArrayList list) {
        if(this.getActivity() != null) {
            WindowManager manager_1 = this.getActivity().getWindowManager();
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager_1.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int size = (int) (width / 3);

            LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewRecommed.setLayoutManager(manager);
            recyclerviewRecommed.setAdapter(new Review_RecyclerAdapter(list, size));
        }
    }

    @Override
    public void getLatestReview(ArrayList list) {

        if(this.getActivity()!= null){
        WindowManager manager_1 = this.getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager_1.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int size = (int)(width/3);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewLastest.setLayoutManager(manager);
        recyclerviewLastest.setAdapter(new Review_RecyclerAdapter(list,size));
    }}
}
