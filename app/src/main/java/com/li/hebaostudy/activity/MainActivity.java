package com.li.hebaostudy.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.ActionTypeContacts;
import com.li.hebaostudy.application.BaseFragmentActivity;
import com.li.hebaostudy.fragment.ChangebagFragment;
import com.li.hebaostudy.fragment.HomeFragment;
import com.li.hebaostudy.fragment.InvestFragment;
import com.li.hebaostudy.fragment.MycenterFragment;
import com.li.hebaostudy.util.AppManager;
import com.li.hebaostudy.util.status.StatusBarUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @copyright:北京爱钱帮财富科技有限公司
 * 功能描述: 主界面
 * 作 者:  李晓楠
 * 时 间： 2016/12/26 17:30
 */
public class MainActivity extends BaseFragmentActivity {


    @BindView(R.id.fragment_container)
    RelativeLayout fragmentContainer;//fragment填充器
    @BindView(R.id.img_home)
    ImageView imgHome; //首页图标
    @BindView(R.id.img_changebag)
    ImageView imgChangebag; //零钱包图片
    @BindView(R.id.img_invest)
    ImageView imgInvest; //投资图片
    @BindView(R.id.img_mycenter)
    ImageView imgMycenter;//我的图片
    @BindView(R.id.text_home)
    TextView textHome; //首页文字
    @BindView(R.id.text_changebag)
    TextView textChangebag; //零钱包
    @BindView(R.id.text_invest)
    TextView textInvest; //
    @BindView(R.id.text_mycenter)
    TextView textMycenter;


    @OnClick({R.id.img_home, R.id.img_changebag, R.id.img_invest, R.id.img_mycenter,
            R.id.text_home, R.id.text_changebag, R.id.text_invest, R.id.text_mycenter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_home:
                goFragment(0);
                break;
            case R.id.img_changebag:
                goFragment(1);
                break;
            case R.id.img_invest:
                goFragment(2);
                break;
            case R.id.img_mycenter:
                goFragment(3);
                break;
            case R.id.text_home:
                goFragment(0);
                break;
            case R.id.text_changebag:
                goFragment(1);
                break;
            case R.id.text_invest:
                goFragment(2);
                break;
            case R.id.text_mycenter:
                goFragment(3);
                break;
        }
    }

    /**
     * handle 消息处理对象 使用弱引用持有外部activity的引用 防止内存泄露的情况产生
     */
    private MyHandler mHandler = new MyHandler(this);
    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = (MainActivity) reference.get();
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    activity.textHome.performClick();
                    break;
                case 1:
                    activity.textChangebag.performClick();
                    break;
                case 2:
                    activity.textInvest.performClick();
                    break;
                case 3:
                    activity.textMycenter.performClick();
                    break;
                default:
                    break;
            }
        }
    }


    private Fragment[] fragments;
    private HomeFragment homeFragment;//首页fragment
    private ChangebagFragment changebagFragment;//零钱包fragment
    private InvestFragment investFragment;//投资fragment
    private MycenterFragment mycenterFragment;//我的个人中心的fragment

    private int index;// 将要展示那个fragment
    private int currentTabIndex;// 页面当前展示的是那个fragment
    private int flag;// 跳转标记 0 首页 1 零钱包  2 投资 3 我的个人中心

    private LocalReceiver receiver;//广播处理器
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //沉浸式状态栏的显示
        StatusBarUtil.setTransparent(this, false);
        //获取进入标记
        flag = getIntent().getIntExtra("flag", 0);

        homeFragment = new HomeFragment();
        changebagFragment = new ChangebagFragment();
        investFragment = new InvestFragment();
        mycenterFragment = new MycenterFragment();
        fragments = new Fragment[]{homeFragment, changebagFragment,
                investFragment, mycenterFragment};

        // 根据不同的进入的标志添加fragment
        switch (flag) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, homeFragment)
                        .addToBackStack(null)
                        .show(homeFragment).commit();
                textHome.setSelected(true);
                imgHome.setSelected(true);
                break;
            case 3:
                textMycenter.setSelected(true);
                imgMycenter.setSelected(true);

                Message message = mHandler.obtainMessage();
                message.what = 3;
                mHandler.sendMessage(message);
                break;
            default:
                textHome.setSelected(true);
                imgHome.setSelected(true);
                break;
        }
        initBroadcast();
    }

    /**
     * 注册广播
     */
    private  void initBroadcast(){
        // 初始化广播
        receiver = new LocalReceiver();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionTypeContacts.Brocast_Gotochangebag);
        broadcastManager.registerReceiver(receiver, intentFilter);
    }

    /**
     * 跳转那个fragment
     * @param gonum
     */
    private void goFragment(int gonum){
        index=gonum;
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            //隐藏当前的fragment 并判断要添加的是否在FragmentTransaction中 没有添加上有则显示出来
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
                trx.addToBackStack(null);
                if (index == 3) {
                    /* 创建一个Bundle用来存储数据，传递到Fragment中 */
                    Bundle bundle = new Bundle();
						/* 把数据设置到Fragment中 */
                    fragments[index].setArguments(bundle);
                }
            }
            trx.show(fragments[index]).commit();
            //
            switch (currentTabIndex) {
                case 0:
                    textHome.setSelected(false);
                    imgHome.setSelected(false);
                    break;
                case 1:
                    textChangebag.setSelected(false);
                    imgChangebag.setSelected(false);
                    break;
                case 2:
                    textInvest.setSelected(false);
                    imgInvest.setSelected(false);
                    break;
                case 3:
                    textMycenter.setSelected(false);
                    imgMycenter.setSelected(false);
                    break;

                default:
                    break;
            }
            // 当前的菜单按钮设为选中状态
            switch (index) {
                case 0:
                    textHome.setSelected(true);
                    imgHome.setSelected(true);
                    break;
                case 1:
                    textChangebag.setSelected(true);
                    imgChangebag.setSelected(true);
                    break;
                case 2:
                    textInvest.setSelected(true);
                    imgInvest.setSelected(true);
                    break;
                case 3:
                    textMycenter.setSelected(true);
                    imgMycenter.setSelected(true);
                    break;
                default:
                    break;
            }
            currentTabIndex = index;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isExit();
    }
    /**
     *  退出应用
     */
    private  void isExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您确定要退出么？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppManager.getAppManager().AppExit();
                dialog.dismiss();// 取消dialog，或dismiss掉
            }
        });
        if (!isFinishing()) {
            builder.create().show();
        }
    }
    /**
     * 广播处理器
     */
    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ActionTypeContacts.Brocast_Gotochangebag.equals(action)) {
                textInvest.performClick();
            }
        }
    }
}
