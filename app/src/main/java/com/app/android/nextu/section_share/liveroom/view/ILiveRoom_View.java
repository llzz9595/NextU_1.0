package com.app.android.nextu.section_share.liveroom.view;

import com.app.android.nextu.section_share.liveroom.model.impl_LiveRoom_Model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/20.
 */
public interface ILiveRoom_View {

    void init(ArrayList<impl_LiveRoom_Model> users);
    void reflash(ArrayList<impl_LiveRoom_Model> users);
}
