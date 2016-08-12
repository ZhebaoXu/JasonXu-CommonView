package com.jasonxu.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMyToast().show();
    }


    private Toast getMyToast(){
        View layout = getLayoutInflater().inflate(R.layout.mytoast_layout,(ViewGroup) findViewById(R.id.my_toast));
        TextView content = (TextView)layout.findViewById(R.id.tv_toast);
        Toast myToast = new Toast(getApplicationContext());
        content.setText("这里是新增项目,需要传进来的啊");
        myToast.setGravity(Gravity.CENTER,0,0);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(layout);
        return myToast;
    }
}
