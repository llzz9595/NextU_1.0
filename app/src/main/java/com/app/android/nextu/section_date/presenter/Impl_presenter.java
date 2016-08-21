package com.app.android.nextu.section_date.presenter;

import android.os.Handler;
import android.os.Looper;

import com.app.android.nextu.section_date.model.Impl_modelDetail;
import com.app.android.nextu.section_date.view.IView;

/**
 * Created by SYSTEM on 2016/8/9.
 */
public class Impl_presenter implements IPresenter {

    private IView view;
    private Handler handler;


    public Impl_presenter(IView view) {
        this.view = view;
        handler = new Handler(Looper.getMainLooper());
    }

//    private String id;
    @Override
    public void getTeacherInfo(final String id) {

        final String myid= id;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Impl_modelDetail model = new Impl_modelDetail();
//                model = model.getTeacherInfo(myid);
                model.setTeacher_Name("黎教授");
                model.setTeacher_Type("国际金融");
                model.setTeacher_TypeDetail("广东金融学院大三国际金融法");
                view.initTeacher(model);
            }
        },0);
    }

    @Override
    public void insertTeacherInfo(final String id,  String type) {
        final String myid =id;
        final String myType = type;
        handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                    Impl_modelDetail model = new Impl_modelDetail();
                      model.insertTeacher(myid,myType);
                     view.insertTeacher();
                  }
              },0);
    }

    @Override
    public void deleteTeacherInfo(String id) {
      final String myid = id;
        handler .postDelayed(new Runnable() {
            @Override
            public void run() {
                Impl_modelDetail model = new Impl_modelDetail();
                model.deleteTeacher(myid);
                view.deleteTeacher();
            }
        },0);
    }
}
