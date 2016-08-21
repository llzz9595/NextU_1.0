package com.app.android.nextu.userbaseinfo;

import com.app.android.nextu.userbaseinfo.model.UserBaseInfoModel;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/30.
 */
public interface IUserBaseInfor_View {

    void InitList(ArrayList lists, int num);
    void InitBaseInfo(UserBaseInfoModel user);
}
