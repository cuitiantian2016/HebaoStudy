package com.li.hebaostudy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;
import com.li.hebaostudy.application.ToastUtil;
import com.li.hebaostudy.dialog.fragmentdialog.PayDetailFragment;
import com.li.hebaostudy.view.paydetail.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页模块
 *
 * @author lxn
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.gobuy)
    Button gobuy;
    private PayDetailFragment payDetailFragment;
    private boolean state;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onFirstVisible(Bundle savedInstanceState) {
        super.onFirstVisible(savedInstanceState);
        payDetailFragment=new PayDetailFragment();
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
    }

    @OnClick(R.id.gobuy)
    public void onClick() {
        payDetailFragment.setPasswordCount(6);
        payDetailFragment.setCallback(new Callback() {
            @Override
            public void onForgetPassword() {
                ToastUtil.toastshort("忘记密码");
            }

            @Override
            public void onInputCompleted(CharSequence password) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (state) {
                            payDetailFragment.setPasswordState(true);
                            state = false;
                        } else {
                            payDetailFragment.setPasswordState(false, "密码输入错误");
                            state = true;
                        }
                    }
                },1000);
            }

            @Override
            public void onPasswordCorrectly() {
                payDetailFragment.dismiss();
            }

            @Override
            public void onCancel() {
                //todo:做一些埋点类的需求
            }
        });
        payDetailFragment.show(getActivity().getSupportFragmentManager(),"payDetailFragment");
    }
}
