package com.li.hebaostudy.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述:欢迎页的3D动画
  *   作 者:  李晓楠
  *   时 间： 2016/12/26 11:22 
 */
public class ThreeDRotateAnimation extends Animation {
    private int centerX, centerY;
    private Camera camera = new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 中心点坐标
        centerX = width / 2;
        centerY = height / 2;
        setDuration(500);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        camera.save();
        // 绕y轴旋转
        camera.rotateY(360 * interpolatedTime);
        camera.getMatrix(matrix);
        // 设置翻转中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        camera.restore();
    }
}
