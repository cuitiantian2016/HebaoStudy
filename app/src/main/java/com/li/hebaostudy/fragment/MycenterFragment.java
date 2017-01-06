package com.li.hebaostudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.activity.MymessgeActivity;
import com.li.hebaostudy.application.BaseFragment;
import com.li.hebaostudy.view.ShapeImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 我的个人中心的模块
  *   作 者:  李晓楠
  *   时 间： 2017/1/6 17:26 
 */
public class MycenterFragment extends BaseFragment {


    @BindView(R.id.text_set)
    TextView textSet;
    @BindView(R.id.img_userimg)
    ShapeImageView imgUserimg;
    @BindView(R.id.rel_gift)
    RelativeLayout relGift;
    @BindView(R.id.rel_bell)
    RelativeLayout relBell;
    @BindView(R.id.rel_serve)
    RelativeLayout relServe;
    @BindView(R.id.text_asset)
    TextView textAsset;
    @BindView(R.id.rel_asset)
    RelativeLayout relAsset;
    @BindView(R.id.img_mecard)
    ImageView imgMecard;
    @BindView(R.id.rel_card)
    RelativeLayout relCard;
    @BindView(R.id.rel_share)
    RelativeLayout relShare;

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
    @OnClick({R.id.text_set, R.id.rel_gift, R.id.rel_bell, R.id.rel_serve, R.id.rel_asset, R.id.rel_card, R.id.rel_share})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.text_set:
                //设置
                break;
            case R.id.rel_gift:
                //我的福利
                break;
            case R.id.rel_bell:
                //我的消息列表
                intent.setClass(getActivity(),MymessgeActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_serve:
                //荷包服务
                break;
            case R.id.rel_asset:
                //我的资产
                break;
            case R.id.rel_card:
                //我的银行卡
                break;
            case R.id.rel_share:
                //分享
                
                break;
        }
    }
}
