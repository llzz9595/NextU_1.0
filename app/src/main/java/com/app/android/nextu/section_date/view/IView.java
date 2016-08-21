package com.app.android.nextu.section_date.view;

import com.app.android.nextu.section_date.model.Impl_modelDetail;

/**
 * Created by SYSTEM on 2016/8/9.
 */
public interface IView {


//    Marker getMaker();
//    void setMaker(Marker maker);
//    // 用户获取周边信息初始化 教师信息
    void initTeacher(Impl_modelDetail model);
    void insertTeacher();
    void deleteTeacher();
    void sendMyLocation();
    void changeRtbnTeachToCanel();

}
