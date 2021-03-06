package com.jasonxu.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jasonxu.recyclerview.Adapter.DragListRecyclerAdapter2;
import com.jasonxu.recyclerview.Decoration.DividerItemDecoration;
import com.jasonxu.recyclerview.Entity.ItemData;
import com.jasonxu.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t_xuz on 6/17/16.
 *
 */
public class DragGridFragment extends BaseFragment{

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
        DragListRecyclerAdapter2 mAdapter = new DragListRecyclerAdapter2(R.layout.item_list,itemDataList);
        RecyclerView mRecyclerView = (RecyclerView)view;
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,6,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);

    }
}
