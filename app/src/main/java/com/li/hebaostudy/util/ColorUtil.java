package com.li.hebaostudy.util;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.li.hebaostudy.application.HebaoApplication;

import java.util.regex.Pattern;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: Color颜色使用的工具类
  *   作 者:  李晓楠
  *   时 间： 2016/12/29 14:28 
 */
public class ColorUtil {
    /**
     * 获取资源中的颜色
     * @param color  颜色资源id
     * @return
     */
    public static int getResourcesColor(int color) {

        int ret;
        //ret = HebaoApplication.getInstance().getResources().getColor(color);
        ret = ContextCompat.getColor(HebaoApplication.getInstance(), color);
        return ret;
    }
    /**
     * 将十六进制 颜色代码 转换为 int
     * @param color
     * @return
     */
    public static int HextoColor(String color) {
        // #ff00CCFF
        String reg = "#[a-f0-9A-F]{8}";
        if (!Pattern.matches(reg, color)) {
            color = "#00ffffff";
        }
        return Color.parseColor(color);
    }

    /**
     * 修改颜色透明度
     * @param color
     * @param alpha
     * @return
     */
    public static int changeAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        return Color.argb(alpha, red, green, blue);
    }
}
