package com.app.android.nextu.section_share.selfchannel.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.app.android.nextu.section_share.selfchannel.model.SelfChannelModel;
import com.app.android.nextu.section_share.selfchannel.view.ISelfChannel_View;
import com.app.android.nextu.util.Bag;


/**
 * Created by SYSTEM on 2016/8/1.
 */
public class Impl_SelfChannel_Presenter implements  ISelfChannel_Presenter {

    private ISelfChannel_View view;
    private Handler handler;

    public Impl_SelfChannel_Presenter(ISelfChannel_View view) {
        this.view = view;
        handler = new android.os.Handler(Looper.getMainLooper());
    }

    private Bag<SelfChannelModel>[] bags;
    private   SelfChannelModel model;
    @Override
    public void GetTwoFromList() {
        model = new SelfChannelModel();
        model.GetTwoFromList();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//
//                SelfChannelModel model = new SelfChannelModel();
////                model.setSelfChannel_Poster(R.drawable.example_4);
//                model.setSelfChannel_Title("今天打台风吗");
//                model.setSelfChannel_Date("2016-8-01");
//
//                SelfChannelModel model2 = new SelfChannelModel();
////                model2.setSelfChannel_Poster(R.drawable.ic_example_6);
//                model2.setSelfChannel_Title("今天不打台风");
//                model2.setSelfChannel_Date("2016-8-01");
//                SelfChannelModel model3 = new SelfChannelModel();
////                model3.setSelfChannel_Poster(R.drawable.example3);
//                model3.setSelfChannel_Title("今天不打台风");
//                model3.setSelfChannel_Date("2016-8-01");

                bags = new Bag[8];
               for(int i = 0; i < 8; i++)
                   bags[i] = new Bag<SelfChannelModel>();
//                bags[0].add(model);
//                bags[0].add(model2);
//                bags[1].add(model3);
//                bags[1].add(model2);
//                bags[2].add(model3);
//                bags[2].add(model2);
//                bags[3].add(model);
//                bags[3].add(model3);
//                bags[4].add(model);
//                bags[4].add(model2);
//                bags[5].add(model3);
//                bags[5].add(model2);
//                bags[6].add(model);
//                bags[6].add(model2);
//                bags[7].add(model2);
//                bags[7].add(model3);
                bags = model.getBags();
                Log.e("","intitList");
              if(bags != null)
                view.initList(bags);
            }
        },1000);

    }

    @Override
    public void GetList() {

    }
}
