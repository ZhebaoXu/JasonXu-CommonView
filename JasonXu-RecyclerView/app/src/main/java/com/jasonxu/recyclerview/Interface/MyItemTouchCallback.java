package com.jasonxu.recyclerview.Interface;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by t_xuz on 6/17/16.
 */
public class MyItemTouchCallback extends ItemTouchHelper.Callback{

    private ItemTouchAdapter itemTouchAdapter;
    public MyItemTouchCallback(ItemTouchAdapter itemTouchAdapter) {
        this.itemTouchAdapter = itemTouchAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    public interface ItemTouchAdapter{
        void onMove(int fromPosition,int toPosition);
        void onSwiped(int position);
    }
}
