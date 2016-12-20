package com.jasonxu.searchviewdemo.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by jason_000 on 2016/8/11.
 *
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getContentViewId());

        initData();

        initViews();

        initEvents();
    }

    protected abstract int getContentViewId();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void initData();

}
