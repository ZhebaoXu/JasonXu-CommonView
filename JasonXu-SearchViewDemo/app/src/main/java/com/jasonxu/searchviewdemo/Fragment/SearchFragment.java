package com.jasonxu.searchviewdemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jasonxu.searchviewdemo.Adapter.RecentSearchAdapter;
import com.jasonxu.searchviewdemo.R;
import com.jasonxu.searchviewdemo.Utils.LogUtils;
import com.jasonxu.searchviewdemo.View.FlowLayout.FlowLayout;
import com.jasonxu.searchviewdemo.View.FlowLayout.TagAdapter;
import com.jasonxu.searchviewdemo.View.FlowLayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason_000 on 2016/8/11.
 * 搜索 页面，即刚入该fragment所绑定的activity时，就显示该fragment，这个activity只是提供一个承载fragment的framelayout即可，以并动态添加fragment
 */
public class SearchFragment extends BaseFragment{

    private TagFlowLayout mHotSearch;
    private ListView mRecentSearch;

    //模拟热门搜索的数据源，网易云音乐默认只是从服务器端拉取最新的10条tag信息，这里也是一样
    private List<String> mHotSearchData;
    //模拟最近搜索的数据源，网易云音乐默认只是从服务器拉取最新的5条数据信息，这里也是一样
    private List<String> mRecentSearchData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_layout,container,false);
    }

    @Override
    public void initData() {

        String[] hotData = new String[]{
                "送自己一首歌",
                "李志",
                "灰姑娘与四骑士",
                "We Don't Talk Anymore",
                "爱情废柴",
                "陈莉",
                "安河桥",
                "Diamonds",
                "我的梦",
                "薛之谦"
        };

        String[] recentData = new String[]{
                "夜夜夜夜",
                "当你老了",
                "驿动的心",
                "最后的温柔",
                "笑看风云"
        };

        //将数组转换为集合,
        //注意：虽然Arrays.asList(hotData)能得到list集合，但是是一个不可变的集合，所有要对集合添加删除修改得用可变的集合
        mHotSearchData = new ArrayList<>();
        mHotSearchData.addAll(Arrays.asList(hotData));
        mRecentSearchData = new ArrayList<>();
        mRecentSearchData.addAll( Arrays.asList(recentData));
    }

    @Override
    public void initViews(View view) {
        mHotSearch = (TagFlowLayout)view.findViewById(R.id.tag_flow_layout);
        mRecentSearch = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    public void initEvents() {

        /*设置最大选中1个，这里只是跳转的话，这个设不设置无所谓*/
        mHotSearch.setMaxSelectCount(1);
        final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHotSearch.setAdapter(new TagAdapter<String>(mHotSearchData) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) mInflater.inflate(R.layout.tagflowlayout_textview, parent, false);
                textView.setText(s);
                return textView;
            }
        });

        /*
        * 点击每个tag需要跳转到新的页面中去，这里采用动态添加fragment来创建页面
        * */
        mHotSearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                LogUtils.e("onTagClick---",position+"");

                return false;
            }
        });


        final RecentSearchAdapter mAdapter = new RecentSearchAdapter(mRecentSearchData,mContext);
        mRecentSearch.setAdapter(mAdapter);
        /*
        * 监听叉叉按钮的点击
        * */
        mAdapter.setRemoveListener(new RecentSearchAdapter.RemoveListener() {
            @Override
            public void removeRecentSong(int position) {
                LogUtils.e("madapter---",position+"");
                mRecentSearchData.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        /*
        * 监听listview每个条目的点击
        * */
        mRecentSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击每个条目需要跳转到新的页面，这里采用动态添加fragment来创建页面
                LogUtils.e("listview---",position+"");

            }
        });
    }


}
