package com.app.android.nextu.section_share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.app.android.nextu.R;
import com.app.android.nextu.section_date.Activity_Section_Date;
import com.app.android.nextu.section_share.liveroom.presenter.adapter.ViewPagerAdapter;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class Activity_Section_Share extends AppCompatActivity implements MaterialTabListener {

    MaterialTabHost tabHost;

    //使用注解插件
    ViewPagerAdapter adapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.root)
    FrameLayout root;
    @Bind(R.id.btn_Menu)
    View btnMenu;
    @Bind(R.id.btn_Search)
    ImageButton btnSearch;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.rbtn_SmartPhone)
    RadioButton rbtnSmartPhone;
    @Bind(R.id.rbtn_VideoCam)
    RadioButton rbtnVideoCam;
    @Bind(R.id.rtbn_Extension)
    RadioButton rtbnExtension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liveroom_firstpage);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        View guillotineMenu = LayoutInflater.from(this).
                inflate(R.layout.view_userbaseinfo, null);
        root.addView(guillotineMenu);
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), btnMenu)
                .setStartDelay(250)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();


        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);

        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }


    @OnClick({R.id.btn_Search, R.id.pager, R.id.rbtn_SmartPhone, R.id.rbtn_VideoCam, R.id.rtbn_Extension})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Search:
                break;
            case R.id.pager:
                break;
            case R.id.rbtn_SmartPhone:
                break;
            case R.id.rbtn_VideoCam:
                break;
            case R.id.rtbn_Extension:
                Intent intent = new Intent
                        (this, Activity_Section_Date.class);

                //无切换动画
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }
}

