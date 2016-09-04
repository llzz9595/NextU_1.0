package com.app.android.nextu.section_share.selfchannel.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.liveroom.presenter.adapter.LoopViewPagerAdapter;
import com.app.android.nextu.section_share.selfchannel.model.SelfChannelModel;
import com.app.android.nextu.section_share.selfchannel.presenter.Impl_SelfChannel_Presenter;
import com.app.android.nextu.section_share.selfchannel.presenter.adapter.recycleview.RecycleViewAdapter;
import com.app.android.nextu.util.Bag;
import com.app.android.nextu.widget.loopview.LoopViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by SYSTEM on 2016/7/19.
 */
public class Fragment_VideoRoom_page2 extends Fragment implements ISelfChannel_View {

    @Bind(R.id.loopViewPager_2)
    LoopViewPager loopViewPager2;
    @Bind(R.id.indicator_2)
    CircleIndicator indicator2;
    @Bind(R.id.recyview_Collage)
    RecyclerView recyviewCollage;
    @Bind(R.id.recyview_Job)
    RecyclerView recyviewJob;
    @Bind(R.id.recyview_Makeup)
    RecyclerView recyviewMakeup;
    @Bind(R.id.recyview_TeachAfterClass)
    RecyclerView recyviewTeachAfterClass;
    @Bind(R.id.recyview_Fight)
    RecyclerView recyviewFight;
    @Bind(R.id.recyview_Instrument)
    RecyclerView recyviewInstrument;
    @Bind(R.id.recyview_Hobby)
    RecyclerView recyviewHobby;
    @Bind(R.id.recyview_More)
    RecyclerView recyviewMore;

    private Impl_SelfChannel_Presenter presenter;
    public Fragment_VideoRoom_page2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_video_2page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loopViewPager2.setAdapter(new LoopViewPagerAdapter());
        indicator2.setViewPager(loopViewPager2);
       presenter = new Impl_SelfChannel_Presenter(this);
        presenter.GetTwoFromList();




    }

    @Override
    public void  onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initList(Bag<SelfChannelModel>[] bags) {

if(bags != null) {
    WindowManager manager_1 = this.getActivity().getWindowManager();
    DisplayMetrics outMetrics = new DisplayMetrics();
    manager_1.getDefaultDisplay().getMetrics(outMetrics);
    int width = outMetrics.widthPixels;
//        int height2 = outMetrics.heightPixels;
    int distance_left = dip2px(this.getContext(), 35);
//        int distance_layoutWidth = dip2px(this.getContext(),150);
//        int spacingInPixels = dip2px(this.getContext(),20);
    int size = (int) ((width - 2 * distance_left - dip2px(this.getContext(), 20)) / 2);
    Log.d("", "间距：-------");
    LinearLayoutManager manager = new LinearLayoutManager(this.getContext());

    manager.setOrientation(LinearLayoutManager.HORIZONTAL);

    recyviewCollage.setLayoutManager(manager);

//        recyviewCollage.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

    recyviewCollage.setAdapter(new RecycleViewAdapter(bags[0], size));

    LinearLayoutManager manager2 = new LinearLayoutManager(this.getContext());
    manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
    recyviewJob.setLayoutManager(manager2);
    recyviewJob.setLayoutFrozen(true);
//        recyviewJob.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
    recyviewJob.setAdapter(new RecycleViewAdapter(bags[1], size));

        LinearLayoutManager manager3 = new LinearLayoutManager(this.getContext());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyviewMakeup.setLayoutManager(manager3);

//        recyviewMakeup.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyviewMakeup.setAdapter(new RecycleViewAdapter(bags[2], size));


        LinearLayoutManager manager4 = new LinearLayoutManager(this.getContext());
        manager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyviewTeachAfterClass.setLayoutManager(manager4);
        recyviewTeachAfterClass.setLayoutFrozen(true);
//        recyviewTeachAfterClass.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyviewTeachAfterClass.setAdapter(new RecycleViewAdapter(bags[3], size));

        LinearLayoutManager manager5 = new LinearLayoutManager(this.getContext());
        manager5.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyviewFight.setLayoutManager(manager5);
//        recyviewFight.setLayoutFrozen(true);
//        recyviewFight.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyviewFight.setAdapter(new RecycleViewAdapter(bags[4], size));

        LinearLayoutManager manager6 = new LinearLayoutManager(this.getContext());
        manager6.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyviewInstrument.setLayoutManager(manager6);
//        recyviewInstrument.setLayoutFrozen(true);
//        recyviewInstrument.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyviewInstrument.setAdapter(new RecycleViewAdapter(bags[5], size));

        LinearLayoutManager manager7 = new LinearLayoutManager(this.getContext());
        manager7.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyviewHobby.setLayoutManager(manager7);
//        recyviewHobby.setLayoutFrozen(true);
//        recyviewHobby.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyviewHobby.setAdapter(new RecycleViewAdapter(bags[6], size));

        LinearLayoutManager manager8 = new LinearLayoutManager(this.getContext());
        manager8.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyviewMore.setLayoutManager(manager8);
//        recyviewMore.setLayoutFrozen(true);
//        recyviewMore.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyviewMore.setAdapter(new RecycleViewAdapter(bags[7],size));
}
    }

    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
