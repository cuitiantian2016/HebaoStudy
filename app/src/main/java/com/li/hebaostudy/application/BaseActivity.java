package com.li.hebaostudy.application;

import android.app.Activity;
import android.os.Bundle;

import com.li.hebaostudy.util.AppManager;


/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: activity的基类
  *   作 者:  李晓楠
  *   时 间： 2016/12/26 11:58 
 */
public class BaseActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	
}
