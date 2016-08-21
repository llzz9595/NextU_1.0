package com.app.android.nextu.section_date.teacher_dialog.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.app.android.nextu.R;
import com.app.android.nextu.section_date.Activity_Section_Date;
import com.app.android.nextu.section_date.view.IView;
import com.app.android.nextu.util.map.ToastUtil;
import com.flyco.dialog.widget.popup.base.BasePopup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SYSTEM on 2016/8/12.
 */
public class Dialog_SendLocation extends BasePopup<Dialog_SendLocation> {

    @Bind(R.id.edit_maproom_Type)
    EditText editMaproomType;
    @Bind(R.id.txt_maproom_sendlocation_example_1)
    TextView txtMaproomSendlocationExample1;
    @Bind(R.id.txt_maproom_sendlocation_example_2)
    TextView txtMaproomSendlocationExample2;
    @Bind(R.id.txt_maproom_sendlocation_example_3)
    TextView txtMaproomSendlocationExample3;
    @Bind(R.id.txt_maproom_sendlocation_example_5)
    TextView txtMaproomSendlocationExample5;
    @Bind(R.id.txt_maproom_sendlocation_example_6)
    TextView txtMaproomSendlocationExample6;
    @Bind(R.id.txt_maproom_sendlocation_example_7)
    TextView txtMaproomSendlocationExample7;
    @Bind(R.id.txt_maproom_sendlocation_example_8)
    TextView txtMaproomSendlocationExample8;
    @Bind(R.id.edit_maproom_TypeDetail)
    EditText editMaproomTypeDetail;
    @Bind(R.id.txt_maproom_sendlocation)
    TextView txtMaproomSendlocation;
    private Context mContext;

    private IView view;

    public Dialog_SendLocation(Context context, IView view) {
        super(context);
        mContext = context;
        this.view = view;

    }

    private ViewHolder viewHolder;
    private boolean isSure;

    @Override
    public View onCreatePopupView() {
        widthScale(0.85f);
        heightScale(0.45f);

        View inflate = View.inflate(this.getContext(), R.layout.dialog_sendlocation, null);
        viewHolder = new ViewHolder(inflate);
        //为防止layout界面上的EditText在进入页面时就弹出输入法,隐藏软键盘
        getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(viewHolder.editMaproomType.getWindowToken(), 0);
        String[] province = this.getContext().getResources()
                .getStringArray(R.array.type);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this.getContext().getApplicationContext(),
                R.layout.item_dialog_autotextview, province);
//        AutoCompleteTextView act = (AutoCompleteTextView)inflate. findViewById(R.id.AutoCompleteTextView01);
        viewHolder.editMaproomType.setAdapter(adapter);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        viewHolder.txtMaproomSendlocationExample1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.editMaproomType.setText("管理学");
                    }
                }
        );

        viewHolder.txtMaproomSendlocationExample2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.editMaproomType.setText("计算机网络");
                    }
                }
        );
        viewHolder.txtMaproomSendlocationExample3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.editMaproomType.setText("国际金融");
                    }
                }
        );

        viewHolder.txtMaproomSendlocationExample5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.editMaproomType.setText("会计学原理");
                    }
                }
        );
        viewHolder.txtMaproomSendlocationExample6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.editMaproomType.setText("线性代数");
                    }
                }
        );
        viewHolder.txtMaproomSendlocationExample7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.editMaproomType.setText("统计学");
                    }
                }
        );


        viewHolder.txtMaproomSendlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("", " isSure--"+viewHolder.editMaproomType.getText().toString());
//                setSure(true);
                if (viewHolder.editMaproomType.getText().toString().equals("")
                        | viewHolder.editMaproomType.getText() == null)
                {
                    ToastUtil.show(mContext,"辅导科目不能为空");

                }
                else{

                    SharedPreferences locatin_state =
                            mContext.getSharedPreferences("config", mContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = locatin_state.edit();
                editor.putString("locationstate", Activity_Section_Date.Location_Update);
                editor.commit();
//                    放科目参数
                view.sendMyLocation();
                view.changeRtbnTeachToCanel();
                dismiss();
            }}
        });

    }




    static class ViewHolder {
        @Bind(R.id.edit_maproom_Type)
        AutoCompleteTextView editMaproomType;
        @Bind(R.id.txt_maproom_sendlocation_example_1)
        TextView txtMaproomSendlocationExample1;
        @Bind(R.id.txt_maproom_sendlocation_example_2)
        TextView txtMaproomSendlocationExample2;
        @Bind(R.id.txt_maproom_sendlocation_example_3)
        TextView txtMaproomSendlocationExample3;

        @Bind(R.id.txt_maproom_sendlocation_example_5)
        TextView txtMaproomSendlocationExample5;
        @Bind(R.id.txt_maproom_sendlocation_example_6)
        TextView txtMaproomSendlocationExample6;
        @Bind(R.id.txt_maproom_sendlocation_example_7)
        TextView txtMaproomSendlocationExample7;
        @Bind(R.id.txt_maproom_sendlocation_example_8)
        TextView txtMaproomSendlocationExample8;
        @Bind(R.id.edit_maproom_TypeDetail)
        EditText editMaproomTypeDetail;
        @Bind(R.id.txt_maproom_sendlocation)
        TextView txtMaproomSendlocation;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
