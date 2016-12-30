package com.li.hebaostudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.util.ColorUtil;
import com.li.hebaostudy.util.status.StatusBarUtil;
import com.li.hebaostudy.view.TurnCardListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @copyright:北京爱钱帮财富科技有限公司
 * 功能描述: 我的消息列表的activity
 * 作 者:  李晓楠
 * 时 间： 2016/12/28 16:09
 */
public class MymessgeActivity extends AppCompatActivity {

    @BindView(R.id.card_list)
    TurnCardListView cardList;
    @BindView(R.id.img_topback)
    ImageView imgTopback;
    @BindView(R.id.rel_golast)
    RelativeLayout relGolast;


    private int currentposition;//当前显示的是第几个
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        ButterKnife.bind(this);
        //状态栏着色
        StatusBarUtil.setColor(this, ColorUtil.getResourcesColor(R.color.logo_color));

        //前台展示的是哪个
        cardList.setOnTurnListener(new TurnCardListView.OnTurnListener() {
            @Override
            public void onTurned(int position) {
                currentposition=position;
                if (0 == position) {
                    //当前的是最新的
                    relGolast.setVisibility(View.GONE);
                } else {
                    relGolast.setVisibility(View.VISIBLE);
                }
            }
        });


        cardList.setAdapter(new BaseAdapter() {
            int[] colors = {0xffFF9800, 0xff3F51B5, 0xff673AB7, 0xff006064, 0xffC51162, 0xffFFEB3B, 0xff795548, 0xff9E9E9E};

            @Override
            public int getCount() {
                return colors.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View child, ViewGroup parent) {
                if (child == null) {
                    child = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_mymessage, parent, false);
                }

                ((TextView) child.findViewById(R.id.pos)).setText("" + position);
                child.findViewById(R.id.image).setBackgroundColor(colors[position]);
                return child;
            }
        });

    }
    //点击事件
    @OnClick({R.id.img_topback, R.id.rel_golast})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_topback:
                finish();
                break;
            case R.id.rel_golast:
                int size=currentposition;
                 while (size>0){
                     cardList.turnBy(-1);
                     size--;
                 }
                break;
        }
    }
}
