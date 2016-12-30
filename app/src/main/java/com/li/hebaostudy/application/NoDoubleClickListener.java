package com.li.hebaostudy.application;

import android.view.View;

import java.util.Calendar;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 防止重复点击使用的监听
  *   作 者:  李晓楠
  *   时 间： 2016/12/27 21:50
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME = 500;//防止重复的间隔
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    /**
     * 按钮点击事件
     * @param v
     */
    public void onNoDoubleClick(View v){

    }
}