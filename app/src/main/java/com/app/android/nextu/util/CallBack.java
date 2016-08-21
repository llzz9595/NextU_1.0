package com.app.android.nextu.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;


/**
 * Created by SYSTEM on 2016/7/16.
 */
public class CallBack <T> implements Callback {

   private Parse<T> mParse;
    private Handler mHandler=new UIHandler(this);
    // 向handler 发送信息
    private static final int  CALLBACK_SUCCESS = 0x21;
    private static final int CALLBACK_FAILURE = 0x34;

    // 构造方法

    public CallBack(Parse<T> mParse) {
        if (mParse == null)
            throw new IllegalArgumentException("Parser can't be null");
        this.mParse = mParse;
    }

    // 等用户覆盖
    public void onFailure(Exception e ) {

    }


    public void onResponse(T t) throws JSONException {

    }
    @Override
    public void onFailure(Request request, IOException e) {
        Message message = Message.obtain();
        Log.d("", "Exception e:" + e);
        message.what = CALLBACK_FAILURE;
        message.obj = e;
        // 向hhandler发送信息
        mHandler.sendMessage(message);

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            T parseResult = mParse.parse(response);
            Message message = Message.obtain();
            message.what = CALLBACK_SUCCESS;
            message.obj = parseResult;
            mHandler.sendMessage(message);

        } else {
            Message message = Message.obtain();
            message.what = CALLBACK_FAILURE;
            mHandler.sendMessage(message);
        }
    }


    private  class UIHandler<T> extends  Handler
    {
        private WeakReference mWeakReference;
        public UIHandler(CallBack<T> callback)
        {
            //调用主线程
            super(Looper.getMainLooper());
            mWeakReference = new WeakReference(callback);

        }

        public  void handleMessage( Message message)
        {
            Log.d("","callbacck 触发");
            switch(message.what)
            {
                case CALLBACK_SUCCESS:{
                    T t = (T) message.obj;
                    CallBack callback = (CallBack) mWeakReference.get();
                    if(callback != null)
                        try {
                            callback.onResponse(t);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    break;
                }
                case CALLBACK_FAILURE:{
                    IOException e = (IOException) message.obj;
                    CallBack callback = (CallBack) mWeakReference.get();
                    if (callback != null) {
                        callback.onFailure(e);
                    }

                }
                default:
                    super.handleMessage(message);
                    break;
            }

        }
    }
}
