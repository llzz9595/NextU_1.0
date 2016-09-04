package com.app.android.nextu.section_share.liveroom.model;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.app.android.nextu.section_share.liveroom.http.BaseUrl;
import com.app.android.nextu.section_share.liveroom.json.LiveRoom_JsonToJava;
import com.app.android.nextu.section_share.liveroom.presenter.impl.Impl_LiveRoom_Presenter;
import com.app.android.nextu.util.CallBack;
import com.app.android.nextu.util.StringParse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/7/20.
 */
public class impl_LiveRoom_Model implements Parcelable ,ILiveRoom_Model {

    private String user_Name ;
    private String user_Count;
    private String user_Image;
    private String user_Poster;
    private String user_Title;
    private   Impl_LiveRoom_Presenter presenter;
public impl_LiveRoom_Model(){

//    Impl_LiveRoom_Presenter presenter = new Impl_LiveRoom_Presenter(new I);
}



    public impl_LiveRoom_Model(String user_Count, String user_Image, String user_Name, String user_Poster, String user_Title) {
        this.user_Name = user_Name;
        this.user_Count = user_Count;
        this.user_Image = user_Image;
        this.user_Poster = user_Poster;
        this.user_Title = user_Title;
    }

    public impl_LiveRoom_Model(Parcel in)
    {
        user_Count = in.readString();
        user_Image= in.readString();
        user_Name = in.readString();
        user_Poster = in.readString();
       user_Title = in.readString();
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Count() {
        return user_Count;
    }

    public void setUser_Count(String user_Count) {
        this.user_Count = user_Count;
    }

    public String getUser_Image() {
        return user_Image;
    }

    public void setUser_Image(String user_Image) {
        this.user_Image = user_Image;
    }

    public String getUser_Poster() {
        return user_Poster;
    }

    public void setUser_Poster(String user_Poster) {
        this.user_Poster = user_Poster;
    }

    public String getUser_Title() {
        return user_Title;
    }

    public void setUser_Title(String user_Title) {
        this.user_Title = user_Title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_Count);
        dest.writeString(user_Image);
        dest.writeString(user_Name);
        dest.writeString(user_Poster);
       dest.writeString(user_Title);
    }

    public static final Parcelable.Creator<impl_LiveRoom_Model> CREATOR = new Parcelable.Creator<impl_LiveRoom_Model>() {
        @Override
        public impl_LiveRoom_Model createFromParcel(Parcel parcel) {
            return new impl_LiveRoom_Model(parcel);
        }

        @Override
        public impl_LiveRoom_Model[] newArray(int i) {
            return new impl_LiveRoom_Model[i];
        }

    };

    private ArrayList<impl_LiveRoom_Model> users;
    public void getUserRooms()
    {
        GetRooms test = new GetRooms();
        test.execute();
                // 从数据库返回数据

    }

    public ArrayList<impl_LiveRoom_Model> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<impl_LiveRoom_Model> users) {
        this.users = users;

    }

    private class GetRooms extends AsyncTask<Void,Void,Void>
    {
        ArrayList<impl_LiveRoom_Model> list;
        @Override
        protected Void doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();
            String url = BaseUrl.baseUrlForTomcat+"i/live/popular";
            Request resquest = new Request.Builder()
                    .url(url)
                    .build();
            StringParse parser = new StringParse();
            client.newCall(resquest).enqueue(new CallBack<String>(parser) {
                @Override
                public void onResponse(String s) throws JSONException {
                    Log.e("",  s);
                    LiveRoom_JsonToJava liveRoomJsonToJava = new LiveRoom_JsonToJava();
                    ArrayList<impl_LiveRoom_Model> list = (ArrayList<impl_LiveRoom_Model>) liveRoomJsonToJava.GetData(s);
                    setUsers(list);
                }
            });
            return null;
        }
    }

}