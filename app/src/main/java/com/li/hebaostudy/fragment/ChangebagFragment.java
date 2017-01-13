package com.li.hebaostudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragment;
import com.li.hebaostudy.util.LogUtils;
import com.li.hebaostudy.view.verticalslide.DragLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @copyright:北京爱钱帮财富科技有限公司 功能描述: 零钱包定期的fragment
 * 作 者:  李晓楠
 * 时 间： 2017/1/11 16:01
 */
public class ChangebagFragment extends BaseFragment {


    @BindView(R.id.img_help)
    ImageView imgHelp;
    @BindView(R.id.framlayout_one)
    FrameLayout framlayoutOne;
    @BindView(R.id.framlayout_two)
    FrameLayout framlayoutTwo;
    @BindView(R.id.drag_layout)
    DragLayout dragLayout;
    private ChangebagFragmentItemOne mChangebagFragmentItemOne;
    private ChangebagFragmentItemTwo mChangebagFragmentItemTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle 
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_changebag, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onFirstVisible(Bundle savedInstanceState) {
        super.onFirstVisible(savedInstanceState);
        initEvent();
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
    }

    /**
     * 上下滑动控件的处理
     */
    private void initEvent() {
        //设置拖拽动作监听事件
        DragLayout.ShowNextPageNotifier showNextPageNotifier = new DragLayout
                .ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                LogUtils.i("onDragNext====");
            }

            @Override
            public void onDragTop() {
                LogUtils.i("onDragTop====");
            }
        };
        dragLayout.setNextPageListener(showNextPageNotifier);

        //添加fragment到activity
        mChangebagFragmentItemOne = new ChangebagFragmentItemOne();
        mChangebagFragmentItemTwo = new ChangebagFragmentItemTwo();
        getChildFragmentManager().beginTransaction().add(R.id.framlayout_one, mChangebagFragmentItemOne).add
                (R.id.framlayout_two, mChangebagFragmentItemTwo).commit();
    }

    @OnClick(R.id.img_help)
    public void onClick() {
        
    }
}
