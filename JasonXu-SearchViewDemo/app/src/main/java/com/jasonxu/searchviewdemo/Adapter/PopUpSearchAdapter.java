package com.jasonxu.searchviewdemo.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jasonxu.searchviewdemo.Entity.AssociationData;
import com.jasonxu.searchviewdemo.R;
import com.jasonxu.searchviewdemo.Utils.LogUtils;

import java.util.List;

/**
 * Created by jason_000 on 2016/8/14.
 *
 */
public class PopUpSearchAdapter extends BaseAdapter{
    private List<AssociationData> mListData;
    private Context mContext;
    private LayoutInflater mInflater;
    private RemoveListener removeListener;

    public PopUpSearchAdapter(List<AssociationData> mListData, Context mContext){
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

        if (!TextUtils.isEmpty(mListData.get(position).getAssociate())){
            viewHolder.singName.setText(mListData.get(position).getAssociate());
        }

        viewHolder.removeBtn.setVisibility(View.GONE);

        return convertView;
    }

    static class ViewHolder{
        private TextView singName;
        private ImageButton removeBtn;
    }
}
