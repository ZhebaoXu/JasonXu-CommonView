package com.jasonxu.recyclerview.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasonxu.recyclerview.R;

/**
 * Created by t_xuz on 6/17/16.
 */
public class ItemDragListHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public ImageView image;

    public ItemDragListHolder(View itemView) {
        super(itemView);

        name = (TextView)itemView.findViewById(R.id.item_text);
        image = (ImageView) itemView.findViewById(R.id.item_img);
    }
}
