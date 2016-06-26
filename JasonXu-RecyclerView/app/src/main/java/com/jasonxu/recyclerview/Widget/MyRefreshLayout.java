package com.jasonxu.recyclerview.Widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

public class MyRefreshLayout extends SwipeRefreshLayout {

    private final int mTouchSlop;
    private View mContentView;
    private OnLoadListener mOnLoadListener;

    private float firstTouchY;
    private float lastTouchY;

    private boolean isLoading = false;

    public MyRefreshLayout(Context context) {
        this(context, null);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    //set the child view of RefreshLayout,ListView
    public void setChildView(View mView) {
        this.mContentView = mView;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                firstTouchY = event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                lastTouchY = event.getRawY();
                if (canLoadMore()) {
                    loadData();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    private boolean canLoadMore() {
        return isBottom() && !isLoading && isPullingUp();
    }

    private boolean isBottom() {
        if (mContentView instanceof ListView) {
            ListView mListView = (ListView)mContentView;
            if (mListView.getCount() > 0) {
                if (mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1 &&
                        mListView.getChildAt(mListView.getChildCount() - 1).getBottom() <= mListView.getHeight()) {
                    return true;
                }
            }
        }else if (mContentView instanceof RecyclerView){
            RecyclerView mRecyclerView = (RecyclerView)mContentView;
            if (mRecyclerView.getChildCount()>0){

            }
        }
        return false;
    }

    private boolean isPullingUp() {
        return (firstTouchY - lastTouchY) >= mTouchSlop;
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            setLoading(true);
        }
    }

    public void setLoading(boolean loading) {
        if (mContentView == null) return;
        isLoading = loading;
        if (loading) {
            if (isRefreshing()) {
                setRefreshing(false);
            }
            if (mContentView instanceof ListView) {
                ListView mListView = (ListView)mContentView;
                mListView.setSelection(mListView.getAdapter().getCount() - 1);
            }
            mOnLoadListener.onLoad();
        } else {
            firstTouchY = 0;
            lastTouchY = 0;
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    public interface OnLoadListener {
        public void onLoad();
    }
}