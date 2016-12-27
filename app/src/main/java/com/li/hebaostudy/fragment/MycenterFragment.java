package com.li.hebaostudy.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;

/**
 * 我的新侬模块
 *
 * @author lxn
 */
public class MycenterFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mycenter, container, false);
    }

    @Override
    public void onFirstVisible(Bundle savedInstanceState) {
        super.onFirstVisible(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
        }
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
