package com.app.android.nextu;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by SYSTEM on 2016/7/21.
 */
public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;
    public SharedPreferences locatin_state;
    @Override
    public void onCreate() {
        super.onCreate();
        locatin_state =
                this.getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = locatin_state.edit();
        editor.putString("locationstate", "01");
        editor.commit();
        initTypeface();
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);

    }}