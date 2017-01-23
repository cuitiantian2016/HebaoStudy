package com.li.hebaostudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;
import com.li.hebaostudy.view.ColorArcProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @copyright:北京爱钱帮财富科技有限公司 功能描述: 零钱包定期的fragment种的第一个fragment
 * 作 者:  李晓楠
 * 时 间： 2017/1/11 16:01
 */
public class ChangebagFragmentItemOne extends BaseFragment {


    @BindView(R.id.bar_interest)
    ColorArcProgressBar barInterest;
    @BindView(R.id.text_out)
    TextView textOut;
    @BindView(R.id.text_in)
    TextView textIn;
    @BindView(R.id.text_income)
    TextView textIncome;
    @BindView(R.id.text_asset)
    TextView textAsset;
    @BindView(R.id.lin_asset)
    LinearLayout linAsset;
    @BindView(R.id.text_experience)
    TextView textExperience;
    @BindView(R.id.lin_experience)
    LinearLayout linExperience;
    @BindView(R.id.text_allincome)
    TextView textAllincome;
    @BindView(R.id.lin_allincome)
    LinearLayout linAllincome;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.lin_time)
    LinearLayout linTime;
    @BindView(R.id.img_uppage)
    ImageView imgUppage;
    @BindView(R.id.text_uppage)
    TextView textUppage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_changebag_item1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onFirstVisible(Bundle savedInstanceState) {
        super.onFirstVisible(savedInstanceState);


        barInterest.setCurrentValues(66);
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
