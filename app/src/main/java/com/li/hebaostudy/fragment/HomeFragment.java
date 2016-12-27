package com.li.hebaostudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;

/**
 * 首页模块
 *
 * @author lxn
 */
public class HomeFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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
