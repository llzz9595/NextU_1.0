package com.app.android.nextu.userbaseinfo.model.impl;

import com.app.android.nextu.userbaseinfo.model.IUserBaseInfo_Model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/30.
 */
// 我的缓存
public class MyCacheModel implements IUserBaseInfo_Model {

    private String cachePoster;
    private String cacheTitle;
    private String cacheDate;

    public MyCacheModel() {
    }

    public String getCachePoster() {
        return cachePoster;
    }

    public void setCachePoster(String cachePoster) {
        this.cachePoster = cachePoster;
    }

    public String getCacheTitle() {
        return cacheTitle;
    }

    public void setCacheTitle(String cacheTitle) {
        this.cacheTitle = cacheTitle;
    }

    public String getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(String cacheDate) {
        this.cacheDate = cacheDate;
    }

    @Override
    public ArrayList<MyCacheModel> getMyList() {
        return null;
    }
}
