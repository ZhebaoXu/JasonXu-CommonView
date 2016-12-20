package com.jasonxu.searchviewdemo.Thread;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jasonxu.searchviewdemo.Entity.AssociationData;
import com.jasonxu.searchviewdemo.Entity.Constants;

import java.util.ArrayList;

/**
 * Created by jason_000 on 2016/8/15.
 * 获取联想词runnable接口，此处可以发送网络请求，这里模拟数据
 */
public class GetSearchAssociation implements Runnable{
    private Handler mHandler;
    private Context mContext;

 /*   private GetSearchAssociationInterface getSearchAssociationInterface;

    public GetSearchAssociation(GetSearchAssociationInterface getSearchAssociationInterface){
        this.getSearchAssociationInterface = getSearchAssociationInterface;
    }

    public interface GetSearchAssociationInterface{
        void getAssociationData(List<AssociationData> associationDataLists);
    }*/

    public GetSearchAssociation(Handler mHandler, Context mContext){
        this.mContext = mContext;
        this.mHandler = mHandler;
    }
    @Override
    public void run() {
        //此处模拟数据
        ArrayList<AssociationData> dataLists = new ArrayList<>();
        dataLists.add(new AssociationData(0,"夜空中最亮的星"));
        dataLists.add(new AssociationData(0,"夜的钢琴曲4"));
        dataLists.add(new AssociationData(0,"夜愿"));
        dataLists.add(new AssociationData(0,"夜曲"));
        dataLists.add(new AssociationData(0,"夜夜夜夜"));
        dataLists.add(new AssociationData(0,"夜的第七章"));
        dataLists.add(new AssociationData(0,"夜色钢琴"));
        dataLists.add(new AssociationData(0,"夜闯秋名山"));
        dataLists.add(new AssociationData(0,"夜明"));
        dataLists.add(new AssociationData(0,"夜的钢琴曲二"));

        //通知实现该接口的页面获取数据
        Message msg = new Message();
        msg.what = Constants.HANDLER_ONE;
        Bundle bundle = new Bundle();
        bundle.putSerializable("associate",dataLists);
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }
}
