package com.jasonxu.recyclerview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jasonxu.recyclerview.Entity.ItemData;
import com.jasonxu.recyclerview.Interface.MyItemTouchCallback;
import com.jasonxu.recyclerview.ViewHolder.ItemClickViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by t_xuz on 6/17/16.
 */
public class DragListRecyclerAdapter extends RecyclerView.Adapter<ItemClickViewHolder> implements MyItemTouchCallback.ItemTouchCallbackListener {

    private List<ItemData> dataList;
    private int resId;

    public DragListRecyclerAdapter(int resId, List<ItemData> dataList) {
        this.resId = resId;
        this.dataList = dataList;
    }

    @Override
    public ItemClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new ItemClickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemClickViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        holder.image.setImageResource(dataList.get(position).getImgId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public void onMoveItem(int fromPosition, int toPosition) {

        //最后一个不移动
        if (fromPosition==dataList.size()-1 || toPosition==dataList.size()-1){
            return;
        }

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(dataList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(dataList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onSwipeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }
}
