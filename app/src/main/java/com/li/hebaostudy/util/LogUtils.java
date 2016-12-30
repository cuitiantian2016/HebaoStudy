package com.li.hebaostudy.util;

import android.util.Log;

import com.li.hebaostudy.application.HebaoApplication;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 日志打印的工具类
  *   作 者:  李晓楠
  *   时 间： 2016/12/26 9:42 
 */
public class LogUtils {
	private static String TAG="lixiaonan";
    //private static String TAG=LogUtils.class.getName();

    /**
     * 打印日志
     * @param tag 日志标签
     * @param msg 日志内容
     */
    public static void i(String tag, String msg) {
        if(HebaoApplication.IS_LOG){
            Log.i(tag, msg);
        }
    }

    /**
     * 使用默认标签打印日志
     * @param msg 日志内容
     */
    public static void i(String msg) {
        if(HebaoApplication.IS_LOG){
            Log.i(TAG,msg);
        }
    }
}
