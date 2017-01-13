package com.li.hebaostudy.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.adapter.ProjectItemAdapter;
import com.li.hebaostudy.application.BaseFragment;
import com.li.hebaostudy.bean.ProjectListBean;
import com.li.hebaostudy.util.LogUtils;
import com.li.hebaostudy.view.GradationScrollView;
import com.li.hebaostudy.view.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @copyright:北京爱钱帮财富科技有限公司
 * 功能描述: 投资列表的模块
 * 作 者:  李晓楠
 * 时 间： 2017/1/9 19:16
 */
public class InvestFragment extends BaseFragment {


    @BindView(R.id.listview)
    MyListView listview;//项目列表
    @BindView(R.id.scrollView)
    GradationScrollView scrollView;
    @BindView(R.id.text_title)
    TextView textTitle;//标题
    @BindView(R.id.banner)
    Banner banner;

    private int bannerHeight;//banner的高度
    private List<ProjectListBean> list;//标地列表
    private ProjectItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invest, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onFirstVisible(Bundle savedInstanceState) {
        super.onFirstVisible(savedInstanceState);
        //设置本地图片轮播
        List<Integer> list=new ArrayList<>();
        list.add(R.drawable.banner_local);
        list.add(R.drawable.banner_local);
        banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(
                        "drawable://" +path.toString(),imageView);
            }
        }).start();
        //设置滑动监听
        initScrollListeners();
        //初始化listview
        initlistview();
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        //结束轮播
        banner.stopAutoPlay();
    }

    /**
     * 设置滑动监听
     */
    private void initScrollListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                bannerHeight = banner.getHeight();

                scrollView.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                        if (y <= 0) {
                            //设置标题的背景颜色
                            textTitle.setBackgroundColor(Color.argb(0, 255,183,0));//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= bannerHeight) {
                            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                            float scale = (float) y / bannerHeight;
                            float alpha = (255 * scale);
                            textTitle.setTextColor(Color.argb((int) alpha, 255,255,255));
                            textTitle.setBackgroundColor(Color.argb((int) alpha,255,183,0));
                        } else {
                            //滑动到banner下面设置普通颜色
                            textTitle.setBackgroundColor(Color.argb(255, 255,183,0));
                        }
                    }
                });
            }
        });
    }

    /**
     * 初始化listview
     */
    private void initlistview(){
        //模拟本地的一些数据
        list=new ArrayList<>();

        ProjectListBean one=new ProjectListBean();
        one.setType(1);
        one.setInterest(12.88);
        one.setDeadline(28);
        one.setProjectname("零钱包定期");
        one.setStatus(1);
        ProjectListBean two=new ProjectListBean();
        two.setType(1);
        two.setInterest(13.88);
        two.setDeadline(58);
        two.setProjectname("零钱包定期");
        two.setStatus(1);
        list.add(one);
        list.add(two);
        for (int i=10;i<15;i++){
            ProjectListBean oneitem=new ProjectListBean();
            oneitem.setCountdowntime(i*10000);
            oneitem.setProjectname("企业荷包贷"+i);
            oneitem.setStatus(4);
            oneitem.setDeadline(i+30);
            oneitem.setInterest(0.12+i);
            oneitem.setType(2);
            list.add(oneitem);
        }
        for (int i=0;i<2;i++){
            ProjectListBean oneitem=new ProjectListBean();
            oneitem.setCountdowntime(i*10000);
            oneitem.setProjectname("企业荷包贷"+i);
            oneitem.setStatus(2);
            oneitem.setDeadline(i+30);
            oneitem.setInterest(0.12+i);
            oneitem.setType(2);
            list.add(oneitem);
        }
        for (int i=21;i<25;i++){
            ProjectListBean oneitem=new ProjectListBean();
            oneitem.setCountdowntime(i*10000);
            oneitem.setProjectname("企业荷包贷"+i);
            oneitem.setStatus(3);
            oneitem.setDeadline(i+30);
            oneitem.setInterest(0.12+i);
            oneitem.setType(2);
            list.add(oneitem);
        }

        adapter=new ProjectItemAdapter(getActivity(),list);
        listview.setAdapter(adapter);
    }
}
