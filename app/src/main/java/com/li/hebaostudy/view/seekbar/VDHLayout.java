package com.li.hebaostudy.view.seekbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.li.hebaostudy.R;
import com.li.hebaostudy.util.LogUtils;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 零钱包模块自定义滑动模块的进度条  网址：http://blog.csdn.net/xyzeng12/article/details/53128285
  *   作 者:  李晓楠
  *   时 间： 2017/1/20 16:44 
 */
public class VDHLayout extends LinearLayout {
    private final Paint paint;
    private ViewDragHelper mDragger;
    private View mAutoBackView;
    private Point[] mAutoBackOriginPos =
            {
                    new Point(0, 0),
                    new Point(0, 0),
                    new Point(0, 0),
                    new Point(0, 0),
                    new Point(0, 0),
            };//5个原点坐标数组初始值

    private OnItemSelectListener itemselectlistener;

    public void setItemselectlistener(OnItemSelectListener itemselectlistener) {
        this.itemselectlistener = itemselectlistener;
    }

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);//没这个方法就不执行onDraw，我也不知道为什么
        //1、创建实例
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback()//3、实现ViewDragHelper.CallCack相关方法
        {
            /**
             * 如果子View不消耗事件（clickable=false），那么整个手势（DOWN-MOVE*-UP）都是直接进入onTouchEvent，在onTouchEvent的DOWN的时候就确定了captureView。
             * 如果消耗事件，那么就会先走onInterceptTouchEvent方法，判断是否可以捕获，而在判断的过程中会去判断另外两个回调的方法：
             * getViewHorizontalDragRange和getViewVerticalDragRange，只有这两个方法返回大于0的值才能正常的捕获。
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {//tryCaptureView如果返回ture则表示可以捕获该view
                //mEdgeTrackerView禁止直接移动
                return child == mAutoBackView;
            }

            /**
             * clampViewPositionHorizontal,clampViewPositionVertical可以在该方法中对child移动的边界进行控制，
             * left , top 分别为即将移动到的位置,以下代码表示内部移动效果
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - mAutoBackView.getWidth() - leftBound;
                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return mAutoBackOriginPos[0].y;//水平滑动，y是固定的
            }

            //手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == mAutoBackView) {
                    //判断不同范围回到最近的圆点
                    if (mAutoBackView.getLeft() < mAutoBackOriginPos[1].x / 2) {
                        itemselectlistener.selectitem(1);
                        mDragger.settleCapturedViewAt(mAutoBackOriginPos[0].x, mAutoBackOriginPos[0].y);
                    } else if (mAutoBackView.getLeft() < 3 * mAutoBackOriginPos[1].x / 2) {
                        itemselectlistener.selectitem(2);
                        mDragger.settleCapturedViewAt(mAutoBackOriginPos[1].x, mAutoBackOriginPos[0].y);
                    } else if (mAutoBackView.getLeft() < 5 * mAutoBackOriginPos[1].x / 2) {
                        mDragger.settleCapturedViewAt(mAutoBackOriginPos[2].x, mAutoBackOriginPos[0].y);
                        itemselectlistener.selectitem(3);
                    } else if (mAutoBackView.getLeft() < 7 * mAutoBackOriginPos[1].x / 2) {
                        itemselectlistener.selectitem(4);
                        mDragger.settleCapturedViewAt(mAutoBackOriginPos[3].x, mAutoBackOriginPos[0].y);
                    } else {
                        itemselectlistener.selectitem(5);
                        mDragger.settleCapturedViewAt(mAutoBackOriginPos[4].x, mAutoBackOriginPos[0].y);
                    }
                    invalidate();
                }
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }

        });
        //花圆的画笔
        paint = new Paint();
    }

    //    2、触摸相关方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);//决定我们是否应该拦截当前的事件
    }

    int position;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() > mAutoBackView.getLeft() && event.getX() < mAutoBackView.getRight()) {//按下时在滑块范围内说明要拖动
            mDragger.processTouchEvent(event);//处理触摸事件
        } else {//else滑动滑块到触摸位置最近的圆点
            if (event.getX() - 60 < mAutoBackOriginPos[1].x / 2) {
                //点击该范围移动到第一个点，60是什么我也不知道，不要这个就点不准

                if(event.getAction()==MotionEvent.ACTION_UP){
                    itemselectlistener.selectitem(1);
                }
                position = 0;
            } else if (event.getX() - 60 < 3 * mAutoBackOriginPos[1].x / 2) {
                position = mAutoBackOriginPos[1].x;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    itemselectlistener.selectitem(2);
                }
            } else if (event.getX() - 60 < 5 * mAutoBackOriginPos[1].x / 2) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    itemselectlistener.selectitem(3);
                }
                position = 2 * mAutoBackOriginPos[1].x;
            } else if (event.getX() - 60 < 7 * mAutoBackOriginPos[1].x / 2) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    itemselectlistener.selectitem(4);
                }
                position = 3 * mAutoBackOriginPos[1].x;
            } else {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    itemselectlistener.selectitem(5);
                }
                position = 4 * mAutoBackOriginPos[1].x;
            }
            //1.调用ofInt(int...values)方法创建ValueAnimator对象
            int pNow = mAutoBackView.getLeft();
            ValueAnimator mAnimator = ValueAnimator.ofInt(pNow, position);
            //2.为目标对象的属性变化设置监听器
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 3.为目标对象的属性设置计算好的属性值
                    int animatorValue = (int) animation.getAnimatedValue();
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) mAutoBackView.getLayoutParams();
                    marginLayoutParams.leftMargin = animatorValue;
                    mAutoBackView.setLayoutParams(marginLayoutParams);
                }
            });
            //4.设置动画的持续时间、是否重复及重复次数等属性
            mAnimator.setDuration(Math.abs(pNow - position) * 2);
            mAnimator.setRepeatCount(0);
            mAnimator.setRepeatMode(ValueAnimator.REVERSE);
            //5.为ValueAnimator设置目标对象并开始执行动画
            mAnimator.setTarget(mAutoBackView);
            mAnimator.start();
            invalidate();
        }
        return true;
    }


    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //保存5个原点的位置信息（左边缘坐标）,不懂的参考图片
        for (int i = 0; i < 5; i++) {
            mAutoBackOriginPos[i].x = i * ((r - l) - (mAutoBackView.getRight() - mAutoBackView.getLeft())) / 4;
            mAutoBackOriginPos[i].y = mAutoBackView.getTop();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mAutoBackView = getChildAt(0);//加载view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(getResources().getColor(R.color.tool_redcolor));
        paint.setAntiAlias(true);
        //画圆点
        for (int i = 0; i < 5; i++) {
            canvas.drawCircle(mAutoBackOriginPos[i].x + (mAutoBackView.getRight() - mAutoBackView.getLeft()) / 2,
                    mAutoBackOriginPos[i].y + (mAutoBackView.getBottom() - mAutoBackView.getTop()) / 2, 15, paint);
        }
    }

    public interface OnItemSelectListener{
        void selectitem(int select);
    }
}