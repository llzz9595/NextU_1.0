package com.app.android.nextu.userbaseinfo.model.impl;

import com.app.android.nextu.userbaseinfo.model.IUserBaseInfo_Model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/30.
 */

// 我的收藏
public class MyCollectionModel implements IUserBaseInfo_Model {

    private String collectionPoster;
    private String collectionTitle;
    private String collectionDate;

    public MyCollectionModel() {
    }

    public String getCollectionPoster() {
        return collectionPoster;
    }

    public void setCollectionPoster(String collectionPoster) {
        this.collectionPoster = collectionPoster;
    }

    public String getCollectionTitle() {
        return collectionTitle;
    }

    public void setCollectionTitle(String collectionTitle) {
        this.collectionTitle = collectionTitle;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    @Override
    public ArrayList<MyCollectionModel> getMyList() {
        return null;
    }
}
