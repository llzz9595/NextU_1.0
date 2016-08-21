package com.app.android.nextu.userbaseinfo.model;

/**
 * Created by SYSTEM on 2016/7/31.
 */
public class UserBaseInfoModel {

    private String userPoster;
    private String userName;
    private String userSex;
    private float userLevel;
    private int userNum_Focus;
    private int userNum_Fans;

    private int userNum_Cache;
    private int userNum_Reservation;
    private  int userNum_Live;
    private int userNum_Collection;
    private  int userNum_SelfChannel;

    public UserBaseInfoModel() {
    }

    public String getUserPoster() {
        return userPoster;
    }

    public void setUserPoster(String userPoster) {
        this.userPoster = userPoster;
    }

    public int getUserNum_SelfChannel() {
        return userNum_SelfChannel;
    }

    public void setUserNum_SelfChannel(int userNum_SelfChannel) {
        this.userNum_SelfChannel = userNum_SelfChannel;
    }

    public int getUserNum_Collection() {
        return userNum_Collection;
    }

    public void setUserNum_Collection(int userNum_Collection) {
        this.userNum_Collection = userNum_Collection;
    }

    public int getUserNum_Live() {
        return userNum_Live;
    }

    public void setUserNum_Live(int userNum_Live) {
        this.userNum_Live = userNum_Live;
    }

    public int getUserNum_Reservation() {
        return userNum_Reservation;
    }

    public void setUserNum_Reservation(int userNum_Reservation) {
        this.userNum_Reservation = userNum_Reservation;
    }

    public int getUserNum_Cache() {
        return userNum_Cache;
    }

    public void setUserNum_Cache(int userNum_Cache) {
        this.userNum_Cache = userNum_Cache;
    }

    public int getUserNum_Fans() {
        return userNum_Fans;
    }

    public void setUserNum_Fans(int userNum_Fans) {
        this.userNum_Fans = userNum_Fans;
    }

    public int getUserNum_Focus() {
        return userNum_Focus;
    }

    public void setUserNum_Focus(int userNum_Focus) {
        this.userNum_Focus = userNum_Focus;
    }

    public float getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(float userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserBaseInfoModel getUserBaseInfo(String id)
    {
        //通过id 查询
        return null;

    }
}
