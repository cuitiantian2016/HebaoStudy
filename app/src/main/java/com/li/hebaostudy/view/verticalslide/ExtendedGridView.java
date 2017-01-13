package com.li.hebaostudy.view.verticalslide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.GridView;

/**
 *  @copyright:北京爱钱帮财富科技有限公司
 *  功能描述: 自定义的上下滑动的控件中嵌套的GridView   对触摸事件做处理
 *            源码地址:https://github.com/lyxxman/DragLayout
 *   作 者:  李晓楠
 *   时 间： 2017/1/11 14:57
 */
public class ExtendedGridView extends GridView {
    private static final int MODE_IDLE = 0;
    private static final int MODE_HORIZONTAL = 1;
    private static final int MODE_VERTICAL = 2;

    private int scrollMode;
    private float downX, downY;

    boolean isAtTop = true; // 如果是true，则允许拖动至底部的下一页
    private int mTouchSlop = 4; // 判定为滑动的阈值，单位是像素

    public ExtendedGridView(Context context) {
        super(context);
    }

    public ExtendedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = ev.getRawX();
            downY = ev.getRawY();
            isAtTop = isAtTop();
            scrollMode = MODE_IDLE;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (scrollMode == MODE_IDLE) {
                float xDistance = Math.abs(downX - ev.getRawX());
                float yDistance = Math.abs(downY - ev.getRawY());
                if (xDistance > yDistance && xDistance > mTouchSlop) {
                    scrollMode = MODE_HORIZONTAL;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                } else if (yDistance > xDistance && yDistance > mTouchSlop) {
                    scrollMode = MODE_VERTICAL;
                    if (downY < ev.getRawY() && isAtTop) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断GridView是否在顶部
     *
     * @return 是否在顶部
     */
    public boolean isAtTop() {
        View firstView=null;
        if(getCount()==0){
            return true;
        }
        firstView=getChildAt(0);
        if(firstView!=null){
            if(getFirstVisiblePosition()==0&&firstView.getTop()==getListPaddingTop()){
                return true;
            }
        }else{
            return true;
        }

        return false;
    }

}
