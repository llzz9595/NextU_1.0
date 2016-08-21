package com.app.android.nextu.section_date.teacher_dialog.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.app.android.nextu.R;
import com.app.android.nextu.section_date.model.Impl_modelDetail;
import com.app.android.nextu.util.map.ToastUtil;
import com.flyco.animation.Attention.Swing;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SYSTEM on 2016/8/10.
 */
public class Dialog_TeacherBase extends BaseDialog<Dialog_TeacherBase>
        implements GeocodeSearch.OnGeocodeSearchListener {

    private Context mContext;
    private Marker marker;
    private AMap aMap;
   private  Impl_modelDetail model;

    public Dialog_TeacherBase(Context context ,Marker marker,AMap aMap) {
        super(context);
        mContext = context;
        this.aMap = aMap;
        this.marker = marker;
    }
    private  GeocodeSearch geocoderSearch;
    private ViewHolder viewHolder;

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        heightScale(0.75f);
        showAnim(new Swing());
        View inflate = View.inflate(mContext, R.layout.dialog_teacherbase, null);
        viewHolder = new ViewHolder(inflate);
        inflate.setBackgroundDrawable(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));
         model = (Impl_modelDetail) marker.getObject();
        // 进行逆地理编码
        geocoderSearch = new GeocodeSearch(mContext);
        geocoderSearch.setOnGeocodeSearchListener(this);
        GetAdress getAdress = new GetAdress();
        getAdress.execute(marker.getPosition().latitude,
                marker.getPosition().longitude);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {


        Impl_modelDetail model = (Impl_modelDetail) marker.getObject();

        viewHolder.txtTeacherBaseName.setText(model.getTeacher_Name());
//        viewHolder.txtTeacherBaseAddress.setText("");
        viewHolder.txtTeacherBaseType.setText(model.getTeacher_Type());
        viewHolder.txtTeacherBaseTypeDetail.setText(model.getTeacher_TypeDetail());


        viewHolder.tvTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "18826078906";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                mContext.startActivity(intent);
                dismiss();
            }
        });

        viewHolder.tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    // 逆地理编码回调函数

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        Log.e("","--------回调2");
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                String addressName = result.getRegeocodeAddress().getFormatAddress()
                        + "附近";

                viewHolder.txtTeacherBaseAddress.setText(addressName);
            } else {
                ToastUtil.show(mContext, "noresult");
            }
        } else {
            ToastUtil.showerror(mContext, rCode);
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {


    }

    static class ViewHolder {
        @Bind(R.id.txt_TeacherBase_Name)
        TextView txtTeacherBaseName;
        @Bind(R.id.txt_TeacherBase_Address)
        TextView txtTeacherBaseAddress;
        @Bind(R.id.txt_TeacherBase_Type)
        TextView txtTeacherBaseType;
        @Bind(R.id.txt_TeacherBase_TypeDetail)
        TextView txtTeacherBaseTypeDetail;
        @Bind(R.id.txt_maproom_teached)
        TextView tvTeach;
        @Bind(R.id.txt_maproom_exitDialog)
        TextView tvExit;
        @Bind(R.id.img_TeacherBase_Head)
        CircleImageView imgTeacherBaseHead;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

private  LatLonPoint latLonPoint;
    private class GetAdress extends AsyncTask<Double,String,String>
    {


        @Override
        protected String doInBackground(Double... params) {
//            Log.e("","do in backgrond");
//            GeocodeSearch geocoderSearch = new GeocodeSearch(mContext);//传入context
            Log.e("","latlong-----"+params[0]+"---"+params[1]);
            latLonPoint = new LatLonPoint
                    (params[0], params[1]);
            RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                    GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
            return null;
    }}



}

