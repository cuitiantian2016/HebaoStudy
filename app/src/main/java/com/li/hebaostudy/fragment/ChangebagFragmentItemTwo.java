package com.li.hebaostudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 零钱包定期的fragment中的第2个fragment
  *   作 者:  李晓楠
  *   时 间： 2017/1/11 16:01 
 */
public class ChangebagFragmentItemTwo extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_changebag_item2, container, false);
    }

    @Override
    public void onFirstVisible(Bundle savedInstanceState) {
        super.onFirstVisible(savedInstanceState);
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
    }
}
