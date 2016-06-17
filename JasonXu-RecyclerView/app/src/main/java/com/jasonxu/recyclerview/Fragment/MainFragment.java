package com.jasonxu.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jasonxu.recyclerview.Activity.MainActivity;
import com.jasonxu.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by t_xuz on 6/17/16.
 *
 */
public class MainFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.main_fragment)
    FrameLayout main;
    @BindView(R.id.ly_btn)
    LinearLayout ly_btn;
    @BindView(R.id.item_click_fragment)
    Button itemClick;
    @BindView(R.id.drag_list_fragment)
    Button listDrag;
    @BindView(R.id.drag_grid_fragment)
    Button gridDrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemClick.setOnClickListener(this);
        listDrag.setOnClickListener(this);
        gridDrag.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ((View.OnClickListener)getActivity()).onClick(view);
    }

  /*  @OnClick(R.id.item_click_fragment) void itemClick(){
//        ly_btn.setVisibility(View.GONE);

        mContext.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment,itemClickFragment)
                .commit();
    }

    @OnClick(R.id.drag_list_fragment) void dragListClick(){
//        ly_btn.setVisibility(View.GONE);
        mContext.getSupportFragmentManager().beginTransaction()
                .add(new DragListFragment(),"2")
                .commit();
    }

    @OnClick(R.id.drag_grid_fragment) void dragGridClick(){
//        ly_btn.setVisibility(View.GONE);
        mContext.getSupportFragmentManager().beginTransaction()
                .add(new DragGridFragment(),"3")
                .commit();
    }
*/

}
