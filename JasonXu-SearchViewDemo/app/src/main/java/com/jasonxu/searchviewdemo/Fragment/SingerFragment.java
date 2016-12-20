package com.jasonxu.searchviewdemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jasonxu.searchviewdemo.R;

/**
 * Created by jason_000 on 2016/8/11.
 * 点击每首歌跳转的页面
 */
public class SingerFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_singer_layout,container,false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void initEvents() {

    }
}
