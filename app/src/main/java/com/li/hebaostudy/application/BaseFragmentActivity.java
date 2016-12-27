package com.li.hebaostudy.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.li.hebaostudy.util.AppManager;


/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: FragmentActivity的基类
  *   作 者:  李晓楠
  *   时 间： 2016/12/26 17:55
 */
public abstract class BaseFragmentActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}