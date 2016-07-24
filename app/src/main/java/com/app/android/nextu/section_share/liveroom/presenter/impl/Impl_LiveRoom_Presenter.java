package com.app.android.nextu.section_share.liveroom.presenter.impl;

import android.os.Handler;
import android.os.Looper;

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
    public Impl_LiveRoom_Presenter(ILiveRoom_View liveRoomView)
    {

        //传递界面信息
        this.liveRoomView = liveRoomView;
        // 初始化handler对象
        handler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void getRooms() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//
                // 数据库得到编译好并处理好的json 数据 users
                // 测试用
                users = new ArrayList<impl_LiveRoom_Model>();
                users.add(new impl_LiveRoom_Model
                        ("9999人在看", "headurl", "李教授",
                                "posterurl","企业：如何进行科学的管理"));
                users.add(new impl_LiveRoom_Model
                        ("2222人在看", "headurl", "黄教授",
                                "posterurl","学校：如何进行科学的管理"));
                users.add(new impl_LiveRoom_Model
                        ("1111人在看", "headurl", "林教授",
                                "posterurl", "家庭：如何进行科学的管理"));

//             通知更新页面
                liveRoomView.init(users);
            }
        },0);
    }

    @Override
    public void doShare() {

    }

    @Override
    public void getReflash() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//
                // 数据库得到编译好并处理好的json 数据 users
                // 测试用
                users = new ArrayList<impl_LiveRoom_Model>();
                users.add(new impl_LiveRoom_Model
                        ("99人在看", "headurl", "林教授",
                                "posterurl","企业：如何进行科学的管理"));
                users.add(new impl_LiveRoom_Model
                        ("22人在看", "headurl", "和教授",
                                "posterurl","学校：如何进行科学的管理"));
                users.add(new impl_LiveRoom_Model
                        ("11人在看", "headurl", "大教授",
                                "posterurl", "家庭：如何进行科学的管理"));

//             通知更新页面
                liveRoomView.reflash(users);
            }
        },0);
    }
}
