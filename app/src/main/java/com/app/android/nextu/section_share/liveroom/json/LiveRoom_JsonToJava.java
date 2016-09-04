package com.app.android.nextu.section_share.liveroom.json;

import com.app.android.nextu.section_share.liveroom.model.impl_LiveRoom_Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SYSTEM on 2016/8/29.
 */
public class LiveRoom_JsonToJava {

    public LiveRoom_JsonToJava() {
    }

    public List<impl_LiveRoom_Model> GetData(String s)
    {
        List<impl_LiveRoom_Model> rooms = new ArrayList<impl_LiveRoom_Model>();
//        Log.e("", "list result------9090" + s);
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<=jsonArray.length();i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                impl_LiveRoom_Model model = new impl_LiveRoom_Model();
                model.setUser_Name(obj.getString("user_Name"));
                model.setUser_Count(obj.getString("user_Count"));
                model.setUser_Image(obj.getString("user_Image"));
                model.setUser_Poster(obj.getString("user_Poster"));
                model.setUser_Title(obj.getString("user_Title"));
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
