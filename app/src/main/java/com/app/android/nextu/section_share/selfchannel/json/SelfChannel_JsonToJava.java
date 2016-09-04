package com.app.android.nextu.section_share.selfchannel.json;

import android.util.Log;

import com.app.android.nextu.section_share.selfchannel.model.SelfChannelModel;
import com.app.android.nextu.util.Bag;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by SYSTEM on 2016/8/29.
 */
public class SelfChannel_JsonToJava {


    public SelfChannel_JsonToJava() {
    }

    public Bag<SelfChannelModel>[] GetData(String s)
    {

        Bag<SelfChannelModel>[] bags = (Bag<SelfChannelModel>[])new Bag[2];
        for(int i =0 ; i < bags.length; i++)
           bags[i] = new Bag<SelfChannelModel>();

//        Bag<SelfChannelModel> rooms = new Bag<>();
        Log.e("", "list result------9090" + s);
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<2;i++) {
                Log.e("","///////"+i);
                JSONArray obj1 = jsonArray.getJSONArray(i);
//                Log.e("", "list result------8090" + obj1);
                for(int j = 0; j <obj1.length(); j++) {
                    JSONObject obj = obj1.getJSONObject(j);
                    SelfChannelModel model = new SelfChannelModel();

                    model.setSelfChannel_Title(obj.getString("selfChannel_Title"));
                    model.setSelfChannel_Date(obj.getString("selfChannel_Date"));
                    model.setSelfChannel_Poster(obj.getString("selfChannel_Poster"));
//                   Log.e("" ,"get Self_Title "+model.getSelfChannel_Title());
                    bags[i].add(model);
                    Log.e("","obj1 length---"+obj1.length());
                }


            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }

        Log.e("","bags-------");

        return bags;
//
    }
}
