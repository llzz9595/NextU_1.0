package com.app.android.nextu.userbaseinfo.model.impl;

import com.app.android.nextu.userbaseinfo.model.IUserBaseInfo_Model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/30.
 */

// 我的自频道
public class MySeltChannelModel implements IUserBaseInfo_Model {

    private String SelfChannelPoster;
    private String SelfChannelTitle;


    private String SelfChannelDate;
    public MySeltChannelModel() {
    }

    public String getSelfChannelPoster() {
        return SelfChannelPoster;
    }

    public void setSelfChannelPoster(String selfChannelPoster) {
        SelfChannelPoster = selfChannelPoster;
    }

    public String getSelfChannelTitle() {
        return SelfChannelTitle;
    }

    public void setSelfChannelTitle(String selfChannelTitle) {
        SelfChannelTitle = selfChannelTitle;
    }

    public String getSelfChannelDate() {
        return SelfChannelDate;
    }

    public void setSelfChannelDate(String selfChannelDate) {
        SelfChannelDate = selfChannelDate;
    }

    @Override
    public ArrayList<MySeltChannelModel> getMyList() {
        return null;
    }
}
