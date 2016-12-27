package com.li.hebaostudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;

/**
 * 商品分类的模块
 * 
 * @author lxn
 */
public class InvestFragment extends BaseFragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_invest, container,
				false);
		return view;
	}
	@Override
	public void onFirstVisible(Bundle savedInstanceState) {
		super.onFirstVisible(savedInstanceState);
	}

	@Override
	public void onFragmentResume() {
		super.onFragmentResume();
	}

	@Override
	public void onFragmentPause() {
		super.onFragmentPause();
	}
}
