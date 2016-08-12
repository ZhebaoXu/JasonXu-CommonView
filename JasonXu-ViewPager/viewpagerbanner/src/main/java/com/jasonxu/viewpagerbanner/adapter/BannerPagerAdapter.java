package com.jasonxu.viewpagerbanner.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by t_xuz on 7/2/16.
 *
 */
public class BannerPagerAdapter extends PagerAdapter {
    private Context mContext;
    private BannerPagerAdapterInterface mBannerInterface;

    public BannerPagerAdapter(Context context,BannerPagerAdapterInterface mBannerInterface) {
        this.mContext = context;
        this.mBannerInterface = mBannerInterface;
    }

    public interface BannerPagerAdapterInterface {

        int getDataCount();

        List<Integer> getDataList();
    }

    @Override
    public int getCount() {
        return mBannerInterface.getDataCount() == 0 ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position = position % mBannerInterface.getDataList().size();
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mBannerInterface.getDataList().get(position));
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
