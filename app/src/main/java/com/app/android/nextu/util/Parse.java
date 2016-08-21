package com.app.android.nextu.util;

import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * Created by SYSTEM on 2016/7/16.
 */
public interface Parse<T> {

    T parse(Response response) throws IOException;
}
