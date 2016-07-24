package com.app.android.nextu.section_date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.app.android.nextu.R;
import com.app.android.nextu.section_share.Activity_Section_Share;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SYSTEM on 2016/7/23.
 */
public class Activity_Section_Date extends AppCompatActivity {

    @Bind(R.id.rbtn_SmartPhone)
    RadioButton rbtnSmartPhone;
    @Bind(R.id.rbtn_VideoCam)
    RadioButton rbtnVideoCam;
    @Bind(R.id.rtbn_Extension)
    RadioButton rtbnExtension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maproom);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rbtn_SmartPhone, R.id.rbtn_VideoCam, R.id.rtbn_Extension})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn_SmartPhone:
                Intent intent = new Intent(this, Activity_Section_Share.class);
                //无切换动画
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
            case R.id.rbtn_VideoCam:

                break;
            case R.id.rtbn_Extension:
                break;
        }
    }
}
