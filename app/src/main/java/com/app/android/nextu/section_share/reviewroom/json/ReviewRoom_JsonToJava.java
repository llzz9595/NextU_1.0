package com.app.android.nextu.section_share.reviewroom.json;

import com.app.android.nextu.section_share.reviewroom.model.Impl_ReviewRoom_Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SYSTEM on 2016/8/29.
 */
public class ReviewRoom_JsonToJava {


    public ReviewRoom_JsonToJava() {
    }

    public List<Impl_ReviewRoom_Model> GetData(String s)
    {
        List<Impl_ReviewRoom_Model> rooms = new ArrayList<Impl_ReviewRoom_Model>();
//        Log.e("", "list result------9090" + s);
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<=jsonArray.length();i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Impl_ReviewRoom_Model model = new Impl_ReviewRoom_Model();
                model.setReviewRoom_Comments(obj.getString("reviewRoom_Comments"));
                model.setReviewRoom_Title(obj.getString("reviewRoom_Title"));
                model.setReviewRoom_Date(obj.getString("reviewRoom_Date"));
                model.setReviewRoom_Poster(obj.getString("reviewRoom_Poster"));
                model.setReviewRoom_Type(obj.getString("reviewRoom_Type"));
                rooms.add(model);

//                Log.e("", obj.getString("user_Poster"));
            }}
        catch (Exception e) {

            e.printStackTrace();
        }


        return rooms;
//
    }
}
