package com.app.android.nextu.section_share.selfchannel.model;

import android.os.AsyncTask;
import android.util.Log;

import com.app.android.nextu.section_share.liveroom.http.BaseUrl;
import com.app.android.nextu.section_share.selfchannel.json.SelfChannel_JsonToJava;
import com.app.android.nextu.util.Bag;
import com.app.android.nextu.util.CallBack;
import com.app.android.nextu.util.StringParse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/8/1.
 */
public class SelfChannelModel implements ISelfChannel_Model {

    private String  SelfChannel_Poster;
    private String SelfChannel_Title;
    private  String SelfChannel_Date;

    private String url = BaseUrl.baseUrlForTomcat+"i/selfchannel/popular";

    public SelfChannelModel() {
    }

    public String  getSelfChannel_Poster() {
        return SelfChannel_Poster;
    }

    public void setSelfChannel_Poster(String  selfChannel_Poster) {
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


    private    Bag<SelfChannelModel>[] bags;

    public Bag<SelfChannelModel>[] getBags() {
        return bags;
    }

    public void setBags(Bag<SelfChannelModel>[] bags) {
        this.bags = bags;
    }

    public void GetTwoFromList() {

        GetRooms getRooms = new GetRooms();
        getRooms.execute(url);

    }


    private String currentUrl;
    @Override
    public ArrayList GetList() {
        return null;
    }
    private class GetRooms extends AsyncTask<String,Void,String>
    {
        //        ArrayList<Impl_ReviewRoom_Model> list;
        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
//            String url = "http://192.168.0.102:8080/i/live/popular";
            currentUrl = params[0];
            Request resquest = new Request.Builder()
                    .url(currentUrl)
                    .build();
            StringParse parser = new StringParse();
            client.newCall(resquest).enqueue(new CallBack<String>(parser) {
                @Override
                public void onResponse(String s) throws JSONException {
                    Log.e("","Self---"+ s);
                    SelfChannel_JsonToJava selfChannel_jsonToJava
                            = new SelfChannel_JsonToJava();
                    Bag<SelfChannelModel>[] bags
                            =  selfChannel_jsonToJava.GetData(s);
//                    setUsers(list);

                  setBags(bags);
                }
            });
            return null;
        }
    }





}
