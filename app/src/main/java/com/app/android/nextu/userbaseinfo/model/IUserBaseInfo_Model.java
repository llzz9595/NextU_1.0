package com.app.android.nextu.userbaseinfo.model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/30.
 */
public interface IUserBaseInfo_Model<T> {

    // 获取 个人主页的 缓存list 直播list

    ArrayList<T> getMyList();
}
