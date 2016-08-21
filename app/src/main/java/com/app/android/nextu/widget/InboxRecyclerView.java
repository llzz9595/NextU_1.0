package com.app.android.nextu.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.app.android.nextu.section_share.liveroom.presenter.adapter.recycleview.RecycleViewAdapter;
import com.zzt.inbox.widget.InboxLayoutBase;

/**
 * Created by SYSTEM on 2016/7/29.
 */
public class InboxRecyclerView extends InboxLayoutBase<RecyclerView>  {

   private RecyclerView recyclerView;

    public InboxRecyclerView(Context context) {
        super(context);
    }

    public InboxRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InboxRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected RecyclerView createDragableView(Context context, AttributeSet attrs) {

        recyclerView = new RecyclerView(context);

        return recyclerView;
    }

    public void setAdapter(RecycleViewAdapter adapter){
        recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(LinearLayoutManager manager){
        recyclerView.setLayoutManager(manager);
    }
    @Override
    protected boolean isReadyForDragStart() {
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if(null == adapter ){
            return true;
        }else{
            if( manager.findFirstVisibleItemPosition()<=1 ){
                final View firstVisibleChild = recyclerView.getChildAt(0);
                if(firstVisibleChild != null){
                    return firstVisibleChild.getTop() >= recyclerView.getTop();
                }
            }
        }
        return false;
    }

    @Override
    protected boolean isReadyForDragEnd() {
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (null == adapter ) {
            return true;
        }
        else {
            final int lastItemPosition = manager.getItemCount() - 1;
            final int lastVisiblePosition =manager.findLastVisibleItemPosition();
            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - manager.findFirstVisibleItemPosition();
                final View lastVisibleChild = recyclerView.getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    return lastVisibleChild.getBottom() <= recyclerView.getBottom();
                }
            }
        }
        return false;
    }
}
