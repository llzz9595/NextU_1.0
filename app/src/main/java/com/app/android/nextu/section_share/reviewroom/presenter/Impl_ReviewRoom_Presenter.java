package com.app.android.nextu.section_share.reviewroom.presenter;

import android.os.Handler;
import android.os.Looper;

import com.app.android.nextu.section_share.reviewroom.model.Impl_ReviewRoom_Model;
import com.app.android.nextu.section_share.reviewroom.view.IReviewRoom_View;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/8/4.
 */
public class Impl_ReviewRoom_Presenter implements IReviewRoom_Presenter{

    private IReviewRoom_View view;
    private Handler handlder;

    public Impl_ReviewRoom_Presenter(IReviewRoom_View view) {
        this.view = view;
       handlder = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getPopular() {


        handlder.postDelayed(new Runnable() {
            @Override
            public void run() {

                Impl_ReviewRoom_Model model = new Impl_ReviewRoom_Model();
                model.setReviewRoom_Title("企业：如何进行科学管理");
                model.setReviewRoom_Date("2016/08/02");
                model.setReviewRoom_Type("管理学");
                model.setReviewRoom_Comments("89978");
                Impl_ReviewRoom_Model model2 = new Impl_ReviewRoom_Model();
                model2.setReviewRoom_Title("算法：自顶向下的归并排序");
                model2.setReviewRoom_Date("2016/08/02");
                model2.setReviewRoom_Type("计算机");
                model2.setReviewRoom_Comments("29878");
                ArrayList popularReview = new ArrayList<Impl_ReviewRoom_Presenter>();
                popularReview.add(model2);
                popularReview.add(model);
//                ArrayList popularReview = model.getPopularReview();
                view.getPopularReview(popularReview);
            }
        },0);
    }

    @Override
    public void getLatest() {
        handlder.postDelayed(new Runnable() {
            @Override
            public void run() {

                Impl_ReviewRoom_Model model = new Impl_ReviewRoom_Model();
               model.setReviewRoom_Title("企业：如何进行科学管理");
                model.setReviewRoom_Date("2016/08/02");
                model.setReviewRoom_Type("管理学");
                model.setReviewRoom_Comments("89978");
                Impl_ReviewRoom_Model model2 = new Impl_ReviewRoom_Model();
                model2.setReviewRoom_Title("算法：自顶向下的归并排序");
                model2.setReviewRoom_Date("2016/08/02");
                model2.setReviewRoom_Type("计算机");
                model2.setReviewRoom_Comments("29878");
                ArrayList latestReview = new ArrayList<Impl_ReviewRoom_Presenter>();
                latestReview.add(model);
                latestReview.add(model2);
//                ArrayList latestReview = model.getLatestReview();
                view.getLatestReview(latestReview);
            }
        },0);

    }
}
