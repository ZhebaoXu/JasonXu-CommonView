package com.jasonxu.searchviewdemo.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jasonxu.searchviewdemo.Adapter.PopUpSearchAdapter;
import com.jasonxu.searchviewdemo.Entity.AssociationData;
import com.jasonxu.searchviewdemo.Entity.Constants;
import com.jasonxu.searchviewdemo.Fragment.SearchFragment;
import com.jasonxu.searchviewdemo.R;
import com.jasonxu.searchviewdemo.Thread.GetSearchAssociation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason_000 on 2016/8/11.
 *
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private Fragment childFragment;
    private ImageButton mBackBtn,mRemoveBtn,mSearchBtn;
    private TextView mBottomLine;
    private EditText mSearchText;
    private LinearLayout mTopBarLayout;
    //搜索关键字弹出框
    private PopupWindow mSearchPopup;
    //PopupWindow里的控件
    private TextView popSearchName;
    private ListView popSearchList;

    private List<Fragment> mFragmentLists;
    private SearchHandler mHandler;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_searchview_layout;
    }

    @Override
    protected void initData() {
        //初始化handler对象
        mHandler = new SearchHandler();

        //查询出fragment栈中有多少个fragments
        mFragmentLists = getSupportFragmentManager().getFragments();
    }

    @Override
    protected void initViews() {
        //ImageButton
        mBackBtn = (ImageButton)this.findViewById(R.id.backBtn);
        mRemoveBtn = (ImageButton)this.findViewById(R.id.removeBtn);
        mSearchBtn = (ImageButton)this.findViewById(R.id.searchBtn);

        //TextView
        mBottomLine = (TextView)this.findViewById(R.id.bottomLine);
        //EditText
        mSearchText = (EditText)this.findViewById(R.id.searchText);
        //LinearLayout
        mTopBarLayout = (LinearLayout)this.findViewById(R.id.search_view);
    }

    @Override
    protected void initEvents() {

        //第一次进来的时候加载搜索fragment
        childFragment = getSupportFragmentManager().findFragmentByTag("search_fragment");
        if (childFragment != null && mFragmentLists.contains(childFragment)){
            getSupportFragmentManager().beginTransaction()
                    .show(childFragment)
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.search_container_fly,new SearchFragment(),"search_fragment")
                    .commit();
        }


        //对topBar的各个状态进行监听
        //1.EditTextView的监听
        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //对初始状态重置操作
                if (TextUtils.isEmpty(s)){
                    mBottomLine.setTextColor(getResources().getColor(R.color.app_gray_line));
                    mRemoveBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)){
                    //1.更新各个控件的状态
                    mBottomLine.setTextColor(getResources().getColor(R.color.app_blue_text));
                    mRemoveBtn.setVisibility(View.VISIBLE);
                    //2.请求服务器接口来查找该关键字的服务器数据（服务器提供联想词）
                    new Thread(new GetSearchAssociation(mHandler,SearchActivity.this)).start();

                    //3.弹出popupwindow动态显示服务器返回的东西,在handler中

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //2.为各个控件设置监听器
        mRemoveBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mSearchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.removeBtn:
                //1.清空editTextView的数据
                mSearchText.setText("");
                mSearchText.setHint(getResources().getString(R.string.searchTextHint));

                //2.隐藏popupWindow
                if (mSearchPopup != null){
                    mSearchPopup.dismiss();
                }
                break;
            case R.id.searchBtn:

                break;
        }
    }

    private void initSearchPopup(List<AssociationData> dataList){

        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.popupwindow_layout,null);
        ViewGroup.LayoutParams  layoutParams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
/*        layoutParams.leftMargin = 30;
        layoutParams.rightMargin = 30;*/
        popSearchName = (TextView)rootView.findViewById(R.id.popUp_search_name);
        popSearchList = (ListView)rootView.findViewById(R.id.popUp_listView);

        //对listview做数据填充
        PopUpSearchAdapter searchAdapter = new PopUpSearchAdapter(dataList,this);
        popSearchList.setAdapter(searchAdapter);

        //为里面的listview设置点击监听
        popSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击每个Item都会跳转到新的动态添加的fragment中

            }
        });

        if (mSearchPopup == null){

            mSearchPopup = new PopupWindow(rootView,layoutParams.width,layoutParams.height);
//            mSearchPopup = new PopupWindow(rootView);
            mSearchPopup.setOutsideTouchable(true);

            //设置不要遮挡软键盘
            mSearchPopup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            mSearchPopup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

    }

    /*
        * handler类的使用
        * */
    private class SearchHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.HANDLER_ONE:
                    if (mSearchPopup != null){
                        mSearchPopup.dismiss();
                    }

                    Bundle data = msg.getData();
                    ArrayList<AssociationData> dataArrayList = (ArrayList<AssociationData>) data.getSerializable("associate");
                    if (dataArrayList!=null && dataArrayList.size()>0) {
                        //initPopup
                        initSearchPopup(dataArrayList);

                        mSearchPopup.showAsDropDown(mTopBarLayout,100,0);
                    }
                    break;
                case Constants.HANDLER_TWO:

                    break;
                case Constants.HANDLER_THREE:

                    break;
            }
        }
    }


}
