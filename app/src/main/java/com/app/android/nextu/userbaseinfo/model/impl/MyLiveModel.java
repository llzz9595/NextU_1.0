package com.app.android.nextu.userbaseinfo.model.impl;

import com.app.android.nextu.userbaseinfo.model.IUserBaseInfo_Model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/29.
 */
//我的直播
public class MyLiveModel implements IUserBaseInfo_Model{

    private String LivePoster;
    private String LiveTitle;
    private String LiveDate;

    public MyLiveModel() {
    }

    public String getLivePoster() {
        return LivePoster;
    }

    public void setLivePoster(String livePoster) {
        LivePoster = livePoster;
    }

    public String getLiveTitle() {
        return LiveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        LiveTitle = liveTitle;
    }

    public String getLiveDate() {
        return LiveDate;
    }

    public void setLiveDate(String liveDate) {
        LiveDate = liveDate;
    }

    @Override
    public ArrayList<MyLiveModel> getMyList() {
        return null;
    }
}
