package com.app.android.nextu.util;

import android.util.Log;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by SYSTEM on 2016/7/16.
 */
public class StringParse implements Parse<String> {
    String result;
//    返回 response 中的 String
    @Override
    public String parse(Response response) throws IOException {
        result = response.body().string();
        Log.d("","  String Parse结果:"+result);
        return result;
    }
}
