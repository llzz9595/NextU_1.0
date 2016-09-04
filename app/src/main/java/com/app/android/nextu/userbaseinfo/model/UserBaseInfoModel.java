package com.app.android.nextu.userbaseinfo.model;

import android.os.AsyncTask;
import android.util.Log;

import com.app.android.nextu.section_share.liveroom.http.BaseUrl;
import com.app.android.nextu.userbaseinfo.json.UserBaseInfo_JsonToJava;
import com.app.android.nextu.util.CallBack;
import com.app.android.nextu.util.StringParse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;

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
    // 暂时
    private String url = BaseUrl.baseUrlForTomcat+"i/user/base?id=01";

    public UserBaseInfoModel() {
    }


    @Override
    public String toString() {
        return "UserBaseInfoModel{" +
                "userPoster='" + userPoster + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userLevel=" + userLevel +
                ", userNum_Focus=" + userNum_Focus +
                ", userNum_Fans=" + userNum_Fans +
                ", userNum_Cache=" + userNum_Cache +
                ", userNum_Reservation=" + userNum_Reservation +
                ", userNum_Live=" + userNum_Live +
                ", userNum_Collection=" + userNum_Collection +
                ", userNum_SelfChannel=" + userNum_SelfChannel +
                '}';
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

        GetUserBaseInfo getUserBaseInfo = new GetUserBaseInfo();
        getUserBaseInfo.execute(url);
        //通过id 查询
        return null;

    }
    private  UserBaseInfoModel model;

    public UserBaseInfoModel getModel() {
        return model;
    }

    public void setModel(UserBaseInfoModel model) {
        this.model = model;
    }

    String currentUrl;
    private class GetUserBaseInfo extends AsyncTask<String,Void,String>
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
                    Log.e("", "Self---" + s);
                    UserBaseInfo_JsonToJava userBaseInfo_jsonToJava = new UserBaseInfo_JsonToJava();

                   UserBaseInfoModel model = userBaseInfo_jsonToJava.GetData(s);

//                    setUsers(list);

                setModel(model);
                }
            });
            return null;
        }
    }


}
