package com.li.hebaostudy.application;

import android.widget.Toast;

import com.li.hebaostudy.util.LogUtils;
import com.li.hebaostudy.util.StringUtils;


/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: Toast使用工具类
  *   作 者:  李晓楠
  *   时 间： 2016/12/28 16:18 
 */
public class ToastUtil {
	/**
	 * 短时间弹出toast
	 * @param str  toast内容
     */
	public static void toastshort(String str){
		synchronized (ToastUtil.class){
			if(StringUtils.isBlank(str)){
				return;
			}
			Toast.makeText(HebaoApplication.getInstance(),str, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 长时间弹出toast
	 * @param str toast内容
     */
	public static void toastlong(String str){
		synchronized (ToastUtil.class){
			if(StringUtils.isBlank(str)){
				return;
			}
			Toast.makeText(HebaoApplication.getInstance(),str, Toast.LENGTH_LONG).show();
		}
	}
}