package com.li.hebaostudy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.application.BaseFragmentActivity;
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
    View fragmentContainer;//fragment填充器
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
                break;
            case R.id.img_changebag:
                break;
            case R.id.img_invest:
                break;
            case R.id.img_mycenter:
                break;
            case R.id.text_home:
                break;
            case R.id.text_changebag:
                break;
            case R.id.text_invest:
                break;
            case R.id.text_mycenter:
                break;
        }
    }

    /**
     * handle 消息处理对象 使用弱引用持有外部activity的引用 防止内存泄露的情况产生
     */
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

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //沉浸式状态栏的显示
        StatusBarUtil.setTransparent(this, false);


    }

    public void isExit() {
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
}
