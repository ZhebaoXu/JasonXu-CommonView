package com.jasonxu.recyclerview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jasonxu.recyclerview.Entity.ItemData;

import java.util.List;

import ViewHolder.ItemClickViewHolder;

/**
 * Created by t_xuz on 6/17/16.
 *
 */
public class ItemClickRecyclerAdapter extends RecyclerView.Adapter<ItemClickViewHolder>{

    private List<ItemData> dataList;
    private int resId;

    public ItemClickRecyclerAdapter(int resId, List<ItemData>  dataList) {
        this.resId = resId;
        this.dataList = dataList;
    }

    @Override
    public ItemClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
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
}
