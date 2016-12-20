package com.jasonxu.searchviewdemo.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jasonxu.searchviewdemo.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button search;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {}

    @Override
    protected void initViews() {
        search = (Button)this.findViewById(R.id.btn_search);
    }

    @Override
    protected void initEvents() {
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                break;
        }
    }
}
