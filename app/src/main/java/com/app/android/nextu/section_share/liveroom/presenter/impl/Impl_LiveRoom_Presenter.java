package com.app.android.nextu.section_share.liveroom.presenter.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.app.android.nextu.section_share.liveroom.model.impl_LiveRoom_Model;
import com.app.android.nextu.section_share.liveroom.presenter.ILiveRoom_Presenter;
import com.app.android.nextu.section_share.liveroom.view.ILiveRoom_View;

import java.util.ArrayList;


/**
 * Created by SYSTEM on 2016/7/20.
 */
public class Impl_LiveRoom_Presenter implements ILiveRoom_Presenter {
    private ILiveRoom_View liveRoomView;
    private ArrayList<impl_LiveRoom_Model> users;
    private Handler handler;

    public Impl_LiveRoom_Presenter() {
    }

    public Impl_LiveRoom_Presenter(ILiveRoom_View liveRoomView)
    {

        //传递界面信息
        this.liveRoomView = liveRoomView;
        // 初始化handler对象
        handler = new Handler(Looper.getMainLooper());
        users = new ArrayList<impl_LiveRoom_Model>();
    }

    private  impl_LiveRoom_Model model;
   @Override
    public void getRoomsData()
    {
        model = new impl_LiveRoom_Model();
        model.getUserRooms();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                users = model.getUsers();
//                Log.e("", "users size --" + users.size());
                liveRoomView.init(users);
            }
        }, 1000);


    }



    @Override
    public void getRooms(String result) {
//       Handler handler = new Handler(Looper.getMainLooper());
       String s = result;


        Log.e("","解析完毕");
    }



    @Override
    public void doShare() {

    }

    @Override
    public void getReflash() {
        model = new impl_LiveRoom_Model();
        model.getUserRooms();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                users = model.getUsers();
//                Log.e("", "users size --" + users.size());
                liveRoomView.reflash(users);
            }
        }, 1000);

    }
}
