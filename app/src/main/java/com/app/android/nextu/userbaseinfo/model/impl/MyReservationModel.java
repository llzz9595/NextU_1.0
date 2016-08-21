package com.app.android.nextu.userbaseinfo.model.impl;

import com.app.android.nextu.userbaseinfo.model.IUserBaseInfo_Model;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/30.
 */
//我的预约
public class MyReservationModel implements IUserBaseInfo_Model{

    private String reservationPoster;
    private String reservationTitle;
    private String reservationDate;

    public MyReservationModel() {
    }

    public String getReservationPoster() {
        return reservationPoster;
    }

    public void setReservationPoster(String reservationPoster) {
        this.reservationPoster = reservationPoster;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTitle() {
        return reservationTitle;
    }

    public void setReservationTitle(String reservationTitle) {
        this.reservationTitle = reservationTitle;
    }

    @Override
    public ArrayList<MyReservationModel> getMyList() {
        return null;
    }
}
