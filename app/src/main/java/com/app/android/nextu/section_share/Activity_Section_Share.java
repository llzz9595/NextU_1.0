package com.app.android.nextu.section_share;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.app.android.nextu.R;
import com.app.android.nextu.section_date.Activity_Section_Date;
import com.app.android.nextu.section_share.liveroom.presenter.adapter.ViewPagerAdapter;
import com.app.android.nextu.userbaseinfo.IUserBaseInfor_View;
import com.app.android.nextu.userbaseinfo.model.UserBaseInfoModel;
import com.app.android.nextu.userbaseinfo.presenter.IUserBaseInfo_Presenter;
import com.app.android.nextu.userbaseinfo.presenter.adapter.InBoxListAdapter_MyCollection;
import com.app.android.nextu.userbaseinfo.presenter.adapter.InBoxListAdapter_MyLive;
import com.app.android.nextu.userbaseinfo.presenter.adapter.InboxListAdapter_MyCache;
import com.app.android.nextu.userbaseinfo.presenter.adapter.InboxListAdapter_MyReservation;
import com.app.android.nextu.userbaseinfo.presenter.impl.impl_UserBaseInfo_Presenter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;
import com.yalantis.guillotine.animation.GuillotineAnimation;
import com.zzt.inbox.interfaces.OnDragStateChangeListener;
import com.zzt.inbox.widget.InboxBackgroundScrollView;
import com.zzt.inbox.widget.InboxLayoutBase;
import com.zzt.inbox.widget.InboxLayoutListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lankton.anyshape.AnyshapeImageView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
//import com.readystatesoftware.systembartint.SystemBarTintManager;

public class Activity_Section_Share extends AppCompatActivity
        implements MaterialTabListener, IUserBaseInfor_View {

    MaterialTabHost tabHost;

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


    private SharedPreferences locatin_state;
    private InboxLayoutListView inboxLayoutListlView;
    private TextView txtMyToolTitle;
    private IUserBaseInfo_Presenter baseInfoPresenter;

    private AnyshapeImageView   imgHomeHead;
    private TextView txtHomeName,txtHomeLevel, txtHomeFocus, txtHomeFans,
    txtHomeMessage,  txtHomeNumCache, txtHomeNumReservation,  txtHomeNumLive,
            txtHomeNumCollection,  txtHomeNumSelfChannel;
    private  ImageView imgHomeSex;

    private  RoundCornerProgressBar progressHomeLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liveroom_firstpage);
        ButterKnife.bind(this);



        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#32b16c"));



        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);



        View guillotineMenu = LayoutInflater.from(this).
                inflate(R.layout.view_userbaseinfo, null);
        root.addView(guillotineMenu);
        imgHomeHead = (AnyshapeImageView) guillotineMenu.findViewById(R.id.img_Home_Head);

        txtHomeName = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Name);

        imgHomeSex = (ImageView) guillotineMenu.findViewById(R.id.img_Home_Sex);

        txtHomeLevel = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Level);

        progressHomeLevel = (RoundCornerProgressBar) guillotineMenu.findViewById(R.id.progress_Home_Level);

        txtHomeFocus = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Focus);

        txtHomeFans = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Fans);

        txtHomeMessage = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Message);

        txtHomeNumCache = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Num_Cache);

        txtHomeNumReservation = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Num_Reservation);

        txtHomeNumLive = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Num_Live);

        txtHomeNumCollection = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Num_Collection);
        txtHomeNumSelfChannel = (TextView) guillotineMenu.findViewById(R.id.txt_Home_Num_SelfChannel);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu,
                guillotineMenu.findViewById(R.id.guillotine_hamburger), btnMenu)
                .setStartDelay(250)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        txtMyToolTitle = (TextView) findViewById(R.id.txt_MyTool_title);
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

        DisplayMetrics dm = getResources().getDisplayMetrics();

        float value  = value = dm.scaledDensity;
//        List<MyTab> tabs = new LinkedList<MyTab>() ;


        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {


            tabHost.addTab(
                  tabHost.newTab()
                          .setText(adapter.getPageTitle(i))
                          .setTabListener(this)
            );

//            tabs.add(tab);

        }
//        tabHost.setText(5,tabs);

        baseInfoPresenter = new impl_UserBaseInfo_Presenter(this);
        baseInfoPresenter.getUserBaseInfo("1");

        final InboxBackgroundScrollView inboxBackgroundScrollView
                = (InboxBackgroundScrollView) findViewById(R.id.scroll);
        inboxLayoutListlView = (InboxLayoutListView) findViewById(R.id.inbox_ListView);


        inboxLayoutListlView.setBackgroundScrollView(inboxBackgroundScrollView);
        //绑定scrollview
        inboxLayoutListlView.setCloseDistance(50);
        inboxLayoutListlView.setOnDragStateChangeListener(new OnDragStateChangeListener() {
            @Override
            public void dragStateChange(InboxLayoutBase.DragState state) {
                switch (state) {
                    case CANCLOSE:
                        txtMyToolTitle.setText("个人主页");
                        break;
                    case CANNOTCLOSE:

                        break;
                }
            }
        });



        init();


    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void init() {
        final LinearLayout head_view = (LinearLayout) findViewById(R.id.head_view);
        head_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final LinearLayout huancun = (LinearLayout) findViewById(R.id.layout_huancun);
        huancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseInfoPresenter.getMyCaches();
                txtMyToolTitle.setText("我的缓存");
                inboxLayoutListlView.openWithAnim(huancun);

            }
        });

        final LinearLayout yuyue = (LinearLayout) findViewById(R.id.layout_yuyue);
        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseInfoPresenter.getMyReservations();
                txtMyToolTitle.setText("我的预约");
                inboxLayoutListlView.openWithAnim(yuyue);


            }
        });

        final LinearLayout zhibo = (LinearLayout) findViewById(R.id.layout_zhibo);
        zhibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseInfoPresenter.getMyLives();
                txtMyToolTitle.setText("我的直播");
                inboxLayoutListlView.openWithAnim(zhibo);

            }
        });

        final LinearLayout shoucang = (LinearLayout) findViewById(R.id.layout_shoucang);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseInfoPresenter.getMyCollections();
                txtMyToolTitle.setText("我的收藏");
                inboxLayoutListlView.openWithAnim(shoucang);

            }
        });

        final LinearLayout zipingdao = (LinearLayout) findViewById(R.id.layout_zipingdao);
        zipingdao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseInfoPresenter.getMySelfChannel();
                txtMyToolTitle.setText("我的自频道");
                inboxLayoutListlView.openWithAnim(zipingdao);

            }
        });


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
                overridePendingTransition(0, 0);
                break;
        }
    }


    @Override
    public void InitList(ArrayList lists, int num) {

        switch (num) {
            case 1:
                inboxLayoutListlView.setAdapter
                        (new InboxListAdapter_MyCache(
                                this, R.layout.userbase_underview_item, lists));
                break;
            case 2:
                inboxLayoutListlView.setAdapter
                        (new InBoxListAdapter_MyLive(
                                this, R.layout.userbase_underview_item, lists));
                break;
            case 3:
                inboxLayoutListlView.setAdapter
                        (new InBoxListAdapter_MyLive(
                                this, R.layout.userbase_underview_item, lists));
                break;
            case 4:
                inboxLayoutListlView.setAdapter
                        (new InboxListAdapter_MyReservation(
                                this, R.layout.userbase_underview_item, lists));
                break;
            case 5:
                inboxLayoutListlView.setAdapter
                        (new InBoxListAdapter_MyCollection(
                                this, R.layout.userbase_underview_item, lists));
                break;
        }

    }

    @Override
    public void InitBaseInfo(UserBaseInfoModel user) {

        // 测试 图
//        Picasso.with(this).load(R.drawable.example_4).into(imgHomeHead);;
        imgHomeHead.setImageResource(R.drawable.example_4);
        txtHomeName.setText(user.getUserName());
        if(user.getUserSex().equals("男"))
        Picasso.with(this).load(R.drawable.ic_gender_man).into(imgHomeSex);
         else
            Picasso.with(this).load(R.drawable.ic_gender_woman).into(imgHomeSex);
        txtHomeLevel.setText("LV " + "" + user.getUserLevel());
        progressHomeLevel.setProgress(user.getUserLevel()*10);
        txtHomeFocus.setText("关注  " + user.getUserNum_Focus());
        txtHomeFans.setText("粉丝  " + user.getUserNum_Fans());
        // 未处理
        txtHomeMessage.setText("消息  "+"0");
        txtHomeNumCache.setText(""+user.getUserNum_Cache());
        txtHomeNumReservation.setText(""+user.getUserNum_Reservation());
        txtHomeNumLive.setText(""+user.getUserNum_Live());
        txtHomeNumCollection.setText(""+user.getUserNum_Collection());
        txtHomeNumSelfChannel.setText(""+user.getUserNum_SelfChannel());


    }


}

