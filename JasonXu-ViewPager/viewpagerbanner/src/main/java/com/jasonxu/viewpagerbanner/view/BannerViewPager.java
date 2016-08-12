package com.jasonxu.viewpagerbanner.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.jasonxu.viewpagerbanner.Interface.BannerInterface;

/**
 * Created by t_xuz on 7/2/16.
 */
public class BannerViewPager extends ViewPager implements BannerInterface {
    //默认轮播方向是从右向左播放
    private Direction direction = Direction.LEFT;
    //默认每个item播放时间是3秒
    private int showTimeMillis = 3000;


    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerViewPager(Context context) {
        super(context);
    }

    @Override
    public void setShowTimes(int showTimeMillis) {
        this.showTimeMillis = showTimeMillis;
    }

    @Override
    public void showPrevious() {
        if (direction == Direction.LEFT) {
            play(Direction.RIGHT);
        } else if (direction == Direction.RIGHT) {
            play(Direction.LEFT);
        }
    }

    @Override
    public void showNext() {
        play(direction);
    }

    @Override
    public void start() {
        stop();
        postDelayed(playRunnable, showTimeMillis);
    }

    @Override
    public void stop() {
        removeCallbacks(playRunnable);
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Runnable playRunnable = new Runnable() {
        @Override
        public void run() {
            //开始播放
            play(direction);
        }
    };

    private synchronized void play(Direction direction) {
        PagerAdapter mPagerAdapter = getAdapter();
        if (mPagerAdapter != null) {
            int itemCounts = mPagerAdapter.getCount();
            int currentItem = getCurrentItem();
            switch (direction) {
                case LEFT:
                    if (currentItem++ > itemCounts)
                        currentItem = 0;
                    break;
                case RIGHT:
                    if (currentItem-- < 0)
                        currentItem = itemCounts;
                    break;
            }
            setCurrentItem(currentItem);
        }
        start();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE) {
                    start();
                } else if (state == SCROLL_STATE_DRAGGING) {
                    stop();
                }
            }
        });
    }
}
