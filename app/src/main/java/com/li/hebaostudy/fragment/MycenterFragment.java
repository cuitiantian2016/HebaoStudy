package com.li.hebaostudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.li.hebaostudy.R;
import com.li.hebaostudy.activity.MymessgeActivity;
import com.li.hebaostudy.application.BaseFragment;
import com.li.hebaostudy.util.ColorUtil;
import com.li.hebaostudy.util.status.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的新侬模块
 *
 * @author lxn
 */
public class MycenterFragment extends BaseFragment {

    @BindView(R.id.gomessage)
    Button gomessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycenter, container, false);
        ButterKnife.bind(this, view);
        return view;
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

    @OnClick(R.id.gomessage)
    public void onClick() {
        Intent  intent=new Intent(getActivity(), MymessgeActivity.class);
        startActivity(intent);
    }
}
