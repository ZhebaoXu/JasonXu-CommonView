package com.jasonxu.viewpagerbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jasonxu.viewpagerbanner.Interface.BannerInterface;
import com.jasonxu.viewpagerbanner.adapter.BannerPagerAdapter;
import com.jasonxu.viewpagerbanner.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BannerPagerAdapter.BannerPagerAdapterInterface{

    private BannerViewPager mViewPager;
    private List<Integer> resIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mViewPager = (BannerViewPager)this.findViewById(R.id.mViewPager);

        BannerPagerAdapter mAdapter = new BannerPagerAdapter(this,this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setShowTimes(2000);
        mViewPager.setDirection(BannerInterface.Direction.RIGHT);
        mViewPager.start();


    }

    private void initData(){
        resIds = new ArrayList<>();
        // 模拟几张图片
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.nohttp);
        resIds.add(R.mipmap.nohttp_delete);
        resIds.add(R.mipmap.nohttp_des);
        resIds.add(R.mipmap.nohttp_get);
        resIds.add(R.mipmap.nohttp_head);
        resIds.add(R.mipmap.nohttp_options);
        resIds.add(R.mipmap.nohttp_patch);
        resIds.add(R.mipmap.nohttp_post);
        resIds.add(R.mipmap.nohttp_put);
        resIds.add(R.mipmap.nohttp_trace);
    }

    @Override
    public int getDataCount() {
        return resIds.size();
    }

    @Override
    public List<Integer> getDataList() {
        return resIds;
    }
}
