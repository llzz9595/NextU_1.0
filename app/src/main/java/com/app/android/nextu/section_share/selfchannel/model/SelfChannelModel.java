package com.app.android.nextu.section_share.selfchannel.model;

import com.app.android.nextu.util.Bag;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/8/1.
 */
public class SelfChannelModel implements ISelfChannel_Model {

    private int SelfChannel_Poster;
    private String SelfChannel_Title;
    private  String SelfChannel_Date;

    public SelfChannelModel() {
    }

    public int getSelfChannel_Poster() {
        return SelfChannel_Poster;
    }

    public void setSelfChannel_Poster(int selfChannel_Poster) {
        SelfChannel_Poster = selfChannel_Poster;
    }

    public String getSelfChannel_Title() {
        return SelfChannel_Title;
    }

    public void setSelfChannel_Title(String selfChannel_Title) {
        SelfChannel_Title = selfChannel_Title;
    }

    public String getSelfChannel_Date() {
        return SelfChannel_Date;
    }

    public void setSelfChannel_Date(String selfChannel_Date) {
        SelfChannel_Date = selfChannel_Date;
    }


    public Bag<SelfChannelModel>[] GetTwoFromList() {
        Bag<SelfChannelModel>[] bags = (Bag<SelfChannelModel>[])new Bag[8];


        return bags;
    }

    @Override
    public ArrayList GetList() {
        return null;
    }
}
