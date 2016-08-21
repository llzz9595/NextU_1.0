package com.app.android.nextu.section_date.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.model.Marker;
import com.app.android.nextu.R;
import com.app.android.nextu.section_date.teacher_dialog.view.Dialog_TeacherBase;

/**
 * MInfowindow:地图Marker的Infowindow
 *
 * @author Jinhu
 * @date 2016/3/26
 */
public class MInfowindow implements InfoWindowAdapter {

//    @Bind(R.id.txt_MapRoom_title)
    TextView txtMapRoomTitle;
//    @Bind(R.id.img_righticon)
    ImageView imgRighticon;
    private Activity mActivity;
    private AMap aMap;
   private  Marker marker;
    public MInfowindow(Activity activity,AMap aMap) {

        mActivity = activity;
        this.aMap = aMap;
    }

    @Override
    public View getInfoWindow( Marker paramMarker) {
        Log.e("", "getInfoWindow");
        marker = paramMarker;
        View v = mActivity.getLayoutInflater().inflate(
                R.layout.activity_map_infowindow, null);

        txtMapRoomTitle = (TextView) v.findViewById(R.id.txt_MapRoom_title);
        imgRighticon = (ImageView) v.findViewById(R.id.img_righticon);
//		textV = (TextView) v.findViewById(R.id.id_textView_infowindow_address);
		txtMapRoomTitle.setText(paramMarker.getTitle());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                Dialog_TeacherBase dialog_teacherBase =
                        new Dialog_TeacherBase(mActivity, marker, aMap);

                dialog_teacherBase.show();
//                 Toast.makeText(mActivity,"查看详情",Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

    @Override
    public View getInfoContents(Marker paramMarker) {
        View v = mActivity.getLayoutInflater().inflate(
                R.layout.activity_map_infowindow, null);

        return v;
    }



}
