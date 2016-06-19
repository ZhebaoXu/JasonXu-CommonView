package com.jasonxu.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jasonxu.recyclerview.Adapter.ItemClickRecyclerAdapter;
import com.jasonxu.recyclerview.Entity.ItemData;
import com.jasonxu.recyclerview.Interface.OnRecyclerItemClickListener;
import com.jasonxu.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t_xuz on 6/17/16.
 *
 */
public class ItemClickFragment extends BaseFragment{

    List<ItemData> itemDataList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i=0;i<4;i++){
            itemDataList.add(new ItemData(i*8+0,"收款", R.drawable.takeout_ic_category_brand));
            itemDataList.add(new ItemData(i*8+1,"转账", R.drawable.takeout_ic_category_flower));
            itemDataList.add(new ItemData(i*8+2,"余额宝", R.drawable.takeout_ic_category_fruit));
            itemDataList.add(new ItemData(i*8+3,"手机充值", R.drawable.takeout_ic_category_medicine));
            itemDataList.add(new ItemData(i*8+4,"医疗", R.drawable.takeout_ic_category_motorcycle));
            itemDataList.add(new ItemData(i*8+5,"彩票", R.drawable.takeout_ic_category_public));
            itemDataList.add(new ItemData(i*8+6,"电影", R.drawable.takeout_ic_category_store));
            itemDataList.add(new ItemData(i*8+7,"游戏", R.drawable.takeout_ic_category_sweet));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new RecyclerView(mContext);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemClickRecyclerAdapter itemClickRecyclerAdapter = new ItemClickRecyclerAdapter(R.layout.list_item,itemDataList);

        RecyclerView mRecyclerView = (RecyclerView)view;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(itemClickRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder,int position) {
                Toast.makeText(mContext,"this position is  : "+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder, int position) {
                Toast.makeText(mContext,"this long press position is  : "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
