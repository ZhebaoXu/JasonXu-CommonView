package com.jasonxu.searchviewdemo.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jasonxu.searchviewdemo.R;
import com.jasonxu.searchviewdemo.Utils.LogUtils;

import java.util.List;

/**
 * Created by jason_000 on 2016/8/14.
 *
 */
public class RecentSearchAdapter extends BaseAdapter{
    private List<String> mListData;
    private Context mContext;
    private LayoutInflater mInflater;
    private RemoveListener removeListener;

    public RecentSearchAdapter(List<String> mListData,Context mContext){
        this.mListData = mListData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface RemoveListener{
        void removeRecentSong(int position);
    }

    public void setRemoveListener(RemoveListener removeListener){
        this.removeListener = removeListener;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView ==null){
            convertView = mInflater.inflate(R.layout.listview_item_layout,null);
            viewHolder =new ViewHolder();
            viewHolder.singName = (TextView)convertView.findViewById(R.id.singName);
            viewHolder.removeBtn = (ImageButton)convertView.findViewById(R.id.removeBtn);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if (!TextUtils.isEmpty(mListData.get(position))){
            viewHolder.singName.setText(mListData.get(position));
        }

        viewHolder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除该条目

                LogUtils.e("adapter--li",position+"");

                //通过接口回调方式，把操作扔到页面里操作
                removeListener.removeRecentSong(position);

                //第二种方式：直接在adapter做移除操作
                //点击叉叉，将该条数据从数据源中移除，并通知adapter更新视图，如果是网络上的数据，得通过网络接口，删除服务器端数据库里的数据
                //mListData.remove(position);
                //notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder{
        private TextView singName;
        private ImageButton removeBtn;
    }
}
