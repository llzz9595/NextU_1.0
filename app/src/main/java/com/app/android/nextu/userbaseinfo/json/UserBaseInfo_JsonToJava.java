package com.app.android.nextu.userbaseinfo.json;

import android.util.Log;

import com.app.android.nextu.userbaseinfo.model.UserBaseInfoModel;
import com.google.gson.Gson;

/**
 * Created by SYSTEM on 2016/8/29.
 */
public class UserBaseInfo_JsonToJava {


    public UserBaseInfo_JsonToJava() {
    }

    public UserBaseInfoModel GetData(String s)
    {

        //gson解析
       UserBaseInfoModel model = new UserBaseInfoModel();
        Gson gson = new Gson();
        model = gson.fromJson(s,UserBaseInfoModel.class);
        Log.e("","String "+model.toString());
       return  model;
//
    }
}
