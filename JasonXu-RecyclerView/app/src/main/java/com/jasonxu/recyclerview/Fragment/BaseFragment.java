package com.jasonxu.recyclerview.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jasonxu.recyclerview.Activity.MainActivity;

/**
 * Created by t_xuz on 6/17/16.
 */
public class BaseFragment extends Fragment{

    protected MainActivity mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity)context;
    }




}
