package com.li.hebaostudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 在滚动条中嵌入listview的 处理了listview的高度问题
  *   作 者:  李晓楠
  *   时 间： 2016/7/18 17:50 
 */
public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated constructor stub
		super(context, attrs, defStyle);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
