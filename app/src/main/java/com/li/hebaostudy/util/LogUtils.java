package com.li.hebaostudy.util;

import android.util.Log;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 日志打印的工具类
  *   作 者:  李晓楠
  *   时 间： 2016/12/26 9:42 
 */
public class LogUtils {
	public static String TAG="lixiaonan";


    public static void i(String tag, String msg) {
            Log.i(tag, msg);
    }
    public static void i(String msg) {
    	Log.i(TAG,msg);
    }
}
