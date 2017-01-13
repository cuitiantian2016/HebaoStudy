package com.li.hebaostudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 向上滑动 标题栏渐变使用的滚动条 实现方案:通过监听滑动距离 计算标题栏渐变的透明度动态进行设置
  *   作 者:  李晓楠
  *   时 间： 2017/1/9 16:11 
 */
public class GradationScrollView extends ScrollView {

    /**
	 *  滑动改变监听
	 */
	public interface ScrollViewListener {
		void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy);
	}

	private ScrollViewListener scrollViewListener = null;

	public GradationScrollView(Context context) {
		super(context);
	}

	public GradationScrollView(Context context, AttributeSet attrs,
							   int defStyle) {
		super(context, attrs, defStyle);
	}

	public GradationScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置监听
	 * @param scrollViewListener
     */
	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			//这个方法23 之后才开放 目前为了兼容先写了个接口
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}

}