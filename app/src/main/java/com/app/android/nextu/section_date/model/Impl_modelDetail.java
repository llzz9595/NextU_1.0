package com.app.android.nextu.section_date.model;

/**
 * Created by SYSTEM on 2016/8/8.
 */
public class Impl_modelDetail implements IModel_Detail {

    private String teacher_Poster;
    private String teacher_Name;
    private String teacher_Type;
    private String teacher_TypeDetail;

    public String getTeacher_TypeDetail() {
        return teacher_TypeDetail;
    }

    public void setTeacher_TypeDetail(String teacher_TypeDetail) {
        this.teacher_TypeDetail = teacher_TypeDetail;
    }

    public Impl_modelDetail(String teacher_Poster, String teacher_Name, String teacher_Type) {
        this.teacher_Poster = teacher_Poster;
        this.teacher_Name = teacher_Name;
        this.teacher_Type = teacher_Type;
    }

    public Impl_modelDetail() {
    }

    public String getTeacher_Poster() {
        return teacher_Poster;
    }

    public void setTeacher_Poster(String teacher_Poster) {
        this.teacher_Poster = teacher_Poster;
    }

    public String getTeacher_Name() {
        return teacher_Name;
    }

    public void setTeacher_Name(String teacher_Name) {
        this.teacher_Name = teacher_Name;
    }

    public String getTeacher_Type() {
        return teacher_Type;
    }

    public void setTeacher_Type(String teacher_Type) {
        this.teacher_Type = teacher_Type;
    }

    //获取数据
    @Override
    public Impl_modelDetail getTeacherInfo(String id) {
        return null;
    }

    // 插入数据
    @Override
    public void insertTeacher(String id, String type) {

    }

    //删除数据
    @Override
    public void deleteTeacher(String id) {

    }
}
