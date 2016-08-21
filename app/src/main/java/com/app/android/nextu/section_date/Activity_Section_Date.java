package com.app.android.nextu.section_date;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.nearby.NearbyInfo;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchFunctionType;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.nearby.UploadInfo;
import com.amap.api.services.nearby.UploadInfoCallback;
import com.app.android.nextu.R;
import com.app.android.nextu.section_date.adapter.MInfowindow;
import com.app.android.nextu.section_date.model.Impl_modelDetail;
import com.app.android.nextu.section_date.presenter.Impl_presenter;
import com.app.android.nextu.section_date.teacher_dialog.view.Dialog_SendLocation;
import com.app.android.nextu.section_date.view.IView;
import com.app.android.nextu.section_share.Activity_Section_Share;
import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.SlideExit.SlideTopExit;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lankton.anyshape.AnyshapeImageView;


/**
 * Created by SYSTEM on 2016/7/23.
 */
public class Activity_Section_Date extends AppCompatActivity
        implements LocationSource, AMap.OnMarkerClickListener,
        AMapLocationListener,
        IView,
        NearbySearch.NearbyListener {

    @Bind(R.id.rbtn_maproom_SmartPhone)
    RadioButton rbtnSmartPhone;
    @Bind(R.id.rbtn_Teach)
    RadioButton rbtnTeach;
    @Bind(R.id.rtbn_maproon_Extension)
    RadioButton rtbnExtension;
    //    @Bind(R.id.img_MapRoom_Location_Point)
    AnyshapeImageView imgMapRoomLocationPoint;

    private   MarkerOptions myMarkerOptions;
    private  Marker myMaker;
    private UiSettings mUiSettings;
    private Impl_presenter presenter;
    private NearbySearch mNearbySearch;
    public MapView mMapView = null;
    //    public AMapLocationListener mListener;
    public AMap aMap;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lon;
    public static final String Location_Update = "00";
    public static final String Location_NoUpdate = "01";
    private SharedPreferences locatin_state;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private UploadInfo loadInfo ;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.map)
    MapView map;
    @Bind(R.id.mapRoom_Titile)
    TextView mapRoomTitile;

    // 统计上传
    private  int num=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("", "onCreate");
        setContentView(R.layout.layout_maproom);
        ButterKnife.bind(this);

        loadInfo = new UploadInfo();
//        loadInfo.setUserID("01");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        presenter = new Impl_presenter(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#32b16c"));


        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
// 附近功能
        mNearbySearch = NearbySearch.getInstance(getApplicationContext());
        mNearbySearch.addNearbyListener(this);
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(this);


        init();
        aMap.setOnMarkerClickListener(this);
        aMap.setInfoWindowAdapter(new MInfowindow(this, aMap));


        beginSearchTask();
        // 用sharePrefence设置按钮状态

        locatin_state =
                this.getSharedPreferences("config", MODE_PRIVATE);
        if(locatin_state.getString("locationstate","").equals(Location_Update))
        {
            changeRtbnTeachToCanel();
        }
        else changeRtbnTeachToAdd();
        Log.e("","----location state-----"+locatin_state.getString("locationstate",""));
//        s SharedPreferences.Editor editor = locatin_state.edit();
//        editor.putString("locationstate", Location_NoUpdate);
//        editor.commit();
    }


    @Override
    public void onUserInfoCleared(int i) {

        Log.e("","---onUserInfoClear---"+i);
    }

    //周边检索的回调函数
    public void onNearbyInfoSearched(NearbySearchResult nearbySearchResult,
                                     int resultCode) {
        //搜索周边附近用户回调处理
        if (resultCode == 1000) {
            if (nearbySearchResult != null
                    && nearbySearchResult.getNearbyInfoList() != null
                    && nearbySearchResult.getNearbyInfoList().size() > 0) {
                NearbyInfo nearbyInfo = nearbySearchResult.getNearbyInfoList().get(0);
                Log.e("", "获得id" + nearbyInfo.getUserID());
                Log.e("", " 距离：" + nearbyInfo.getDistance());
                Log.e("", "经纬：" + nearbyInfo.getPoint());

            } else {

                Log.e("", "周边搜索结果为空");

            }
            DrawNearBy(nearbySearchResult.getNearbyInfoList());
        } else {
            Log.e("", "周边搜索出现异常，异常码为：" + resultCode);
        }
    }

    @Override
    public void onNearbyInfoUploaded(int i) {

        Log.e("AAA", "上传信息ＯＫ : " + i);
    }

// 定时任务
    private Handler hanlder;
    private  Runnable runnable;
    private void beginSearchTask()
    {
        hanlder = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // run
                Log.e("","run search");
                search();
                hanlder.postDelayed(this,5000);
            }
        };

        hanlder.postDelayed(runnable, 5000);
    }



    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();
        }
        setUpMap();
        Log.e("", "-------------init map-----------");

    }

    private void setUpMap() {
        Log.e("", "----------setup map-------------");
//        mUiSettings.setMyLocationButtonEnabled(true); // 是否显示默认的定位按钮
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(100000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//        aMap.setLocationSource(this);
//        aMap.setMyLocationEnabled(true);
        //启动定位
        mLocationClient.startLocation();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        hanlder.removeCallbacks(runnable);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        stopSendLocation();
        mLocationClient.onDestroy();//销毁定位客户端。
       NearbySearch.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }

    private void stopSendLocation() {
        Log.e("", "停止上传地址");
        //获取附近实例，并设置要清楚用户的id id为获取用户id

//调用异步清除用户接口
       mNearbySearch.stopUploadNearbyInfoAuto();
//        NearbySearch.getInstance(getApplicationContext())
//                .stopUploadNearbyInfoAuto();
        mNearbySearch.setUserID(currentID);
        mNearbySearch.clearUserInfoAsyn();
        UploadInfo loadInfo = new UploadInfo();
//设置上传位置的坐标系支持AMap坐标数据与GPS数据
        loadInfo.setCoordType(NearbySearch.AMAP);
//设置上传数据位置,位置的获取推荐使用高德定位sdk进行获取
        loadInfo.setPoint(new LatLonPoint(lat+2, lon+2));
//设置上传用户id
        loadInfo.setUserID(currentID);
//调用异步上传接口

        mNearbySearch.uploadNearbyInfoAsyn(loadInfo);
//        mNearbySearch.setUserID(currentID);
//        mNearbySearch.clearUserInfoAsyn();
    }



    @Override
    protected void onStop() {
        super.onStop();
        mLocationClient.stopLocation();//停止定位

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @OnClick({R.id.rbtn_maproom_SmartPhone, R.id.rbtn_Teach, R.id.rtbn_maproon_Extension})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn_maproom_SmartPhone:
                Intent intent = new Intent(this, Activity_Section_Share.class);
                //无切换动画
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.rbtn_Teach:
                // 判断状态
                locatin_state = this.getSharedPreferences("config", MODE_PRIVATE);
                if (locatin_state.getString("locationstate", "")
                        .equals(Location_NoUpdate)) {

                    Dialog_SendLocation dialog_sendLocation
                            = new Dialog_SendLocation(this,this);
                    dialog_sendLocation
                            .showAnim(new BounceBottomEnter())
                            .dismissAnim(new SlideTopExit())
                            .show();


                        Log.e("", "---isSend");
//                        sendMyLocation();
                        // 修改状态


                } else {
                    Toast.makeText(this, "你已经上传了位置 是否取消上传？", Toast.LENGTH_SHORT).show();
                    stopSendLocation();
                    SharedPreferences.Editor editor = locatin_state.edit();
                    editor.putString("locationstate", Location_NoUpdate);
                    editor.commit();
                    changeRtbnTeachToAdd();

                }

            case R.id.rtbn_maproon_Extension:
                break;
        }
    }
@Override
    public void changeRtbnTeachToCanel()
    {
        Drawable drawable = getResources().getDrawable(
                R.drawable.ic_maproom_exitteach);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        rbtnTeach.setCompoundDrawables(null, null, null, drawable);
    }
    public void changeRtbnTeachToAdd()
    {
        Drawable drawable = getResources().getDrawable(
                R.drawable.ic_maproom_sendteach);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        rbtnTeach.setCompoundDrawables(null, null, null, drawable);
    }

private  String currentID ;

@Override
    public void sendMyLocation() {
//    mNearbySearch = NearbySearch.getInstance(getApplicationContext());
//    mNearbySearch.addNearbyListener(this);
    currentID = ""+num;
//    Log.e("","current id ------"+currentID);
//    num++;
    Log.e("","sendMyLocation");
        mNearbySearch.startUploadNearbyInfoAuto(new UploadInfoCallback() {

            //设置自动上传数据和上传的间隔时间
            @Override
            public UploadInfo OnUploadInfoCallback() {
//                       先判断是否登录 若登录了从sharepreference 中获取当前用户的ID
                UploadInfo loadInfo = new UploadInfo();

                // 待
                loadInfo.setCoordType(NearbySearch.AMAP);
                //位置信息
                loadInfo.setPoint(new LatLonPoint(lat + 0.0001, lon + 0.0001));
                //用户id信息

                loadInfo.setUserID(currentID);

                // 根据id 查询基本基本 上传
                // 上传到服务器
                presenter.insertTeacherInfo(currentID, "国际金融");

                return loadInfo;
            }
        }, 10000);
    }

//    private Marker currentMaker;

    private ArrayList<Marker> markers = new ArrayList<Marker>();
    private void DrawNearBy(List<NearbyInfo> list) {
//        MInfowindow mInfowindow = new MInfowindow(this);

        aMap.clear();
        aMap.addMarker(myMarkerOptions);
//        ArrayList<Marker> markers = new ArrayList<Marker>();
        for (NearbyInfo info : list) {
            Log.e("", "---------" + info.getPoint());
            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.icon(bitmapDescriptor);
            markerOptions.position(new LatLng
                    (info.getPoint().getLatitude(), info.getPoint().getLongitude()));
            markerOptions.visible(true);
            Marker marker = aMap.addMarker(markerOptions);

//            aMap.setInfoWindowAdapter(new MInfowindow(this, aMap));
//         根据 info getid 查询 教师基本信息
            presenter.getTeacherInfo(info.getUserID());
            Log.e("", "curentMaker != null");
            // 设置头像
            View view_1 = View.inflate(this, R.layout.view_maproom_point, null);
//            Picasso.with(this).load(model.getTeacher_Poster()).into(imgMapRoomLocationPoint);

            BitmapDescriptor bitmapDescriptor
                    = BitmapDescriptorFactory.fromView(view_1);
            marker.setIcon(bitmapDescriptor);
            Impl_modelDetail model = getModel();
            marker.setTitle(model.getTeacher_Type());
            // setObject
            marker.setObject(model);
            Impl_modelDetail modelTest = (Impl_modelDetail) marker.getObject();

            Log.e("", "end drawing--------" + modelTest.getTeacher_Name());
        }
    }



    private void search() {

        if(locatin_state.getString("locationstate","").equals(Location_NoUpdate))
        {

        mNearbySearch.setUserID(currentID);
        mNearbySearch.clearUserInfoAsyn();
        }

        //设置搜索条件
        NearbySearch.NearbyQuery query = new NearbySearch.NearbyQuery();
//设置搜索的中心点
        query.setCenterPoint(new LatLonPoint(lat, lon));
//设置搜索的坐标体系
        query.setCoordType(NearbySearch.AMAP);
//设置搜索半径
        query.setRadius(100000);


//设置查询的时间
        query.setTimeRange(10000);
//设置查询的方式驾车还是距离
        query.setType(NearbySearchFunctionType.DISTANCE_SEARCH);
//调用异步查询接口
        NearbySearch.getInstance(getApplicationContext())
                .searchNearbyInfoAsyn(query);

    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        setUpMap();

    }

    @Override
    public void deactivate() {

        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker != null) {
            if (marker.isInfoWindowShown())
                marker.hideInfoWindow();
            else {
                marker.showInfoWindow();
                Log.e("", "show Infowindow");
            }
            return true;
        }
        return false;
    }

    private Impl_modelDetail model;

    public Impl_modelDetail getModel() {
        return model;
    }

    public void setModel(Impl_modelDetail model) {
        this.model = model;
    }

    @Override
    public void initTeacher(Impl_modelDetail model) {

        Log.e("", "model Type:" + model.getTeacher_Type());
        setModel(model);
    }

    @Override
    public void insertTeacher() {

        Log.e("", "插入/或更新数据成功");
    }

    @Override
    public void deleteTeacher() {
        Log.e("", "删除数据成功");
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                Log.e("", df.format(date));//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
                amapLocation.getAoiName();//获取当前定位点的AOI信息
                lat = amapLocation.getLatitude();
                lon = amapLocation.getLongitude();
                Log.e("pcw", "lat : " + lat + " lon : " + lon);
                aMap.clear();
                mapRoomTitile.setText(amapLocation.getCity() + amapLocation.getAoiName()
                        + amapLocation.getStreet() + amapLocation.getStreetNum());
                aMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(lat, lon), 19));
                myMarkerOptions = new MarkerOptions();
                myMarkerOptions.position(new LatLng(lat, lon));
                myMarkerOptions.title("我的位置");
                myMarkerOptions.visible(true);
                 myMaker = aMap.addMarker( myMarkerOptions);

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
}