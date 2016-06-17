package com.jasonxu.recyclerview.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.jasonxu.recyclerview.Fragment.DragGridFragment;
import com.jasonxu.recyclerview.Fragment.DragListFragment;
import com.jasonxu.recyclerview.Fragment.ItemClickFragment;
import com.jasonxu.recyclerview.Fragment.MainFragment;
import com.jasonxu.recyclerview.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main, new MainFragment())
                    .commit();
        }
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.item_click_fragment:
                fragment = new ItemClickFragment();
                break;
            case R.id.drag_list_fragment:
                fragment = new DragListFragment();
                break;
            case R.id.drag_grid_fragment:
                fragment = new DragGridFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main,fragment)
                .addToBackStack(null)
                .commit();
    }
}
