package com.app.android.nextu.userbaseinfo.presenter.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.app.android.nextu.userbaseinfo.IUserBaseInfor_View;
import com.app.android.nextu.userbaseinfo.model.UserBaseInfoModel;
import com.app.android.nextu.userbaseinfo.model.impl.MyCacheModel;
import com.app.android.nextu.userbaseinfo.model.impl.MyCollectionModel;
import com.app.android.nextu.userbaseinfo.model.impl.MyLiveModel;
import com.app.android.nextu.userbaseinfo.model.impl.MyReservationModel;
import com.app.android.nextu.userbaseinfo.model.impl.MySeltChannelModel;
import com.app.android.nextu.userbaseinfo.presenter.IUserBaseInfo_Presenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SYSTEM on 2016/7/30.
 */
public class impl_UserBaseInfo_Presenter implements IUserBaseInfo_Presenter {

    private IUserBaseInfor_View userBaseInfor_view;
    private Handler handler;
    private   ArrayList<MyLiveModel> models ;

    public impl_UserBaseInfo_Presenter(IUserBaseInfor_View userBaseInfor_view ) {
        this.userBaseInfor_view = userBaseInfor_view;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getMyCaches() {
        //获取数据库数据


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyCacheModel model = new MyCacheModel();
                ArrayList<MyCacheModel> models = model.getMyList();

                 userBaseInfor_view.InitList(models,1);

                    Log.d("","MyCache 为空");
            }
        },0);

    }

    @Override
    public void getMyLives() {
        //获取数据库数据



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyLiveModel model = new MyLiveModel();
                ArrayList<MyLiveModel> models = new ArrayList<MyLiveModel>();
                model.setLiveTitle("广东金融学院轻知识平台");
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                model.setLiveDate(""+format.format(new Date()));


                models.add(model);
                userBaseInfor_view.InitList(models, 2);
            }
        },0);
    }

    @Override
    public void getMyReservations() {

        //获取数据库数据


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyReservationModel model = new MyReservationModel();
                ArrayList<MyReservationModel> models = model.getMyList();
                userBaseInfor_view.InitList(models,3);

            }
        },0);
    }

    @Override
    public void getMyCollections() {
        //获取数据库数据


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyCollectionModel model = new MyCollectionModel();
                ArrayList<MyCollectionModel> models = model.getMyList();
                userBaseInfor_view.InitList(models,4);

            }
        },0);
    }

    @Override
    public void getMySelfChannel() {


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MySeltChannelModel model = new MySeltChannelModel();
                ArrayList<MySeltChannelModel> models = model.getMyList();
                userBaseInfor_view.InitList(models,5);

            }
        },0);
    }

    @Override
    public void getUserBaseInfo(String id) {



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserBaseInfoModel user = new UserBaseInfoModel();
//                UserBaseInfoModel model = user.getUserBaseInfo(id);
                UserBaseInfoModel model = new UserBaseInfoModel();
                user.setUserName("黎教授");
                user.setUserSex("男");
                user.setUserLevel(9);
                user.setUserNum_Focus(10);
                user.setUserNum_Fans(10);
                user.setUserNum_Cache(0);
                user.setUserNum_Reservation(0);
                user.setUserNum_Live(1);
                user.setUserNum_Collection(0);
                user.setUserNum_SelfChannel(0);
                userBaseInfor_view.InitBaseInfo(user);

            }
        },0);
    }
}
