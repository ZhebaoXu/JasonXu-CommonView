package com.jasonxu.viewpagerbanner.Interface;

/**
 * Created by t_xuz on 7/2/16.
 *
 */
public interface BannerInterface {
    //自动轮播的方向
    enum Direction{
        LEFT,
        RIGHT
    }

    //设置每个item的播放时间,默认3000毫秒
    void setShowTimes(int showTimeMillis);

    //播放上一个
    void showPrevious();

    //播放下一个
    void showNext();

    //开始自动播放
    void start();

    //停止
    void stop();

    //设置轮播方向
    void setDirection(Direction direction);
}
