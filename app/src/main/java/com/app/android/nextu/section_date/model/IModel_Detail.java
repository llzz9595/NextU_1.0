package com.app.android.nextu.section_date.model;

/**
 * Created by SYSTEM on 2016/8/8.
 */
public interface IModel_Detail {

    Impl_modelDetail getTeacherInfo(String id);
    void  insertTeacher(String id , String type);
    void deleteTeacher (String id);

}
