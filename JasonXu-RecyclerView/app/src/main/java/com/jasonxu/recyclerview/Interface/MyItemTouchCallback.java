package com.jasonxu.recyclerview.Interface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.method.MovementMethod;

import java.util.Collections;

/**
 * Created by t_xuz on 6/17/16.
 */
public class MyItemTouchCallback extends ItemTouchHelper.Callback {

    private Drawable backDrawable = null;
    private int backColor = -1;

    private ItemTouchCallbackListener itemTouchCallbackListener;

    public MyItemTouchCallback(ItemTouchCallbackListener itemTouchCallbackListener) {
        this.itemTouchCallbackListener = itemTouchCallbackListener;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /*
                * 用于设置是否处理拖拽事件和滑动事件,以及拖拽和滑动的操作方向,譬如:
                * 如果是列表类型,则会有up与down两个方向,
                * 如果是网格类型,则会有up.down,left,right四个方向.
                * */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) { //列表类型
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//0 代表不处理滑动
            return makeMovementFlags(dragFlags, swipeFlags);

        } else if (recyclerView.getLayoutManager() instanceof GridLayoutManager) { //网格类型
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);

        } else {//其他类型,即瀑布流类型
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    /*
    * 如果上面的getMovementFlags的方法里的dragFlags 是非 0 标记,则:
    * 当我们长按 Item 的时候就会不断回调 onMove 方法,
    * 我们只要在这个onMove方法中获取当前拖拽的item的viewHolder与到达拖拽指定位置的item的viewHolder,
    * 有了这两个viewHolder 我们就可以交换它们的数据集,并调用Adapter的notifyItemMoved 方法来刷新item.
    * */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖拽item对应viewHolder的position
        int toPosition = target.getAdapterPosition(); //得到目标item对应viewHolder的position
        itemTouchCallbackListener.onMoveItem(fromPosition, toPosition);
        return true;
    }

    /*
    * 滑动删除功能,记住上面的getMovementFlags 的 swipeFlags 要非0才有效.
    * */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        itemTouchCallbackListener.onSwipeItem(position);
    }

    /*
    *  长按点击时,为了使操作更友好,可以使长按的item高亮显示,那么可以在这个方法里写使背景色高亮的代码逻辑,拖拽完可以在clearView中
    *  取消高亮背景色
    * */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (backColor == -1 && backDrawable == null) {
                Drawable drawable = viewHolder.itemView.getBackground();
                if (drawable == null) {
                    backColor = 0;
                } else {
                    backDrawable = drawable;
                }
            }
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //滑动时改变Item的透明度
            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(1.0f);
        if (backDrawable != null) viewHolder.itemView.setBackgroundDrawable(backDrawable);
        if (backColor != -1) viewHolder.itemView.setBackgroundColor(backColor);

    }

    public interface ItemTouchCallbackListener {

        void onMoveItem(int fromPosition, int toPosition);

        void onSwipeItem(int position);
    }
}
