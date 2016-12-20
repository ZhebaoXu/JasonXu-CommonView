package com.jasonxu.searchviewdemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jasonxu.searchviewdemo.Activity.BaseActivity;

/**
 * Created by jason_000 on 2016/8/11.
 *
 */
public abstract class BaseFragment extends Fragment{
    protected BaseActivity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (BaseActivity)context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();

        initViews(view);

        initEvents();
    }

    public abstract void initData();

    public abstract void initViews(View view);

    public abstract void initEvents();
}
