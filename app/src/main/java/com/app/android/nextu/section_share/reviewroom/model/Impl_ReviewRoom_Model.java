package com.app.android.nextu.section_share.reviewroom.model;

import android.os.AsyncTask;
import android.util.Log;

import com.app.android.nextu.section_share.liveroom.http.BaseUrl;
import com.app.android.nextu.section_share.reviewroom.json.ReviewRoom_JsonToJava;
import com.app.android.nextu.util.CallBack;
import com.app.android.nextu.util.StringParse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/8/4.
 */
public class Impl_ReviewRoom_Model implements IReviewRoom_Model {

    private String ReviewRoom_Poster;
    private String ReviewRoom_Title;
    private String ReviewRoom_Date;
    private String ReviewRoom_Type;
    private String ReviewRoom_Comments;
    private String urlPopuler = BaseUrl.baseUrlForTomcat+"i/review/popular";
    private String urlLatest = BaseUrl.baseUrlForTomcat+"i/review/lastest";
    public Impl_ReviewRoom_Model() {
    }

    public String getReviewRoom_Type() {
        return ReviewRoom_Type;
    }

    public void setReviewRoom_Type(String reviewRoom_Type) {
        ReviewRoom_Type = reviewRoom_Type;
    }

    public String getReviewRoom_Poster() {
        return ReviewRoom_Poster;
    }

    public void setReviewRoom_Poster(String reviewRoom_Poster) {
        ReviewRoom_Poster = reviewRoom_Poster;
    }

    public String getReviewRoom_Title() {
        return ReviewRoom_Title;
    }

    public void setReviewRoom_Title(String reviewRoom_Title) {
        ReviewRoom_Title = reviewRoom_Title;
    }

    public String getReviewRoom_Date() {
        return ReviewRoom_Date;
    }

    public void setReviewRoom_Date(String reviewRoom_Date) {
        ReviewRoom_Date = reviewRoom_Date;
    }

    public String getReviewRoom_Comments() {
        return ReviewRoom_Comments;
    }

    public void setReviewRoom_Comments(String reviewRoom_Comments) {
        ReviewRoom_Comments = reviewRoom_Comments;
    }

    private  ArrayList<Impl_ReviewRoom_Model> popularModels ;
    private  ArrayList<Impl_ReviewRoom_Model> latestModels ;

    public ArrayList<Impl_ReviewRoom_Model> getPopularModels() {
        return popularModels;
    }

    public void setPopularModels(ArrayList<Impl_ReviewRoom_Model> popularModels) {
        this.popularModels = popularModels;
    }

    public ArrayList<Impl_ReviewRoom_Model> getLatestModels() {
        return latestModels;
    }

    public void setLatestModels(ArrayList<Impl_ReviewRoom_Model> latestModels) {
        this.latestModels = latestModels;
    }

    @Override
    public ArrayList getPopularReview()

    {

        GetRooms  getRooms = new GetRooms();
        getRooms.execute(urlPopuler);
        return null;
    }

    @Override
    public ArrayList getLatestReview() {

//        String url = "http://192.168.0.102:8080/i/review/lastest";
        GetRooms  getRooms = new GetRooms();
        getRooms.execute(urlLatest);
                return null;
    }

    private  String currentUrl;

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
                    Log.e("", s);
                    ReviewRoom_JsonToJava reviewRoomJsonToJava = new ReviewRoom_JsonToJava();
                    ArrayList<Impl_ReviewRoom_Model> list
                            = (ArrayList<Impl_ReviewRoom_Model>) reviewRoomJsonToJava.GetData(s);
//                    setUsers(list);

                    if(currentUrl.equals(urlPopuler))
                       setPopularModels(list);
                    else setLatestModels(list);
                }
            });
            return null;
        }
    }


}
