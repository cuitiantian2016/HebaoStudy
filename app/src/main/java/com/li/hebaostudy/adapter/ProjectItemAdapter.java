package com.li.hebaostudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.li.hebaostudy.R;
import com.li.hebaostudy.bean.ProjectListBean;
import com.li.hebaostudy.view.countdowntimer.CountdownView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @copyright:北京爱钱帮财富科技有限公司 功能描述: 标地列表的适配器
 * 作 者:  李晓楠
 * 时 间： 2017/1/10 19:13
 */
public class ProjectItemAdapter extends BaseAdapter {
    private Context _context;
    private List<ProjectListBean> _list;

    public ProjectItemAdapter(Context context, List<ProjectListBean> list) {
        super();
        this._context = context;
        this._list = list;
    }

    public List<ProjectListBean> get_list() {
        return _list;
    }

    public void set_list(List<ProjectListBean> _list) {
        this._list = _list;
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return _list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ProjectListBean oneitem = (ProjectListBean) getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = View.inflate(_context, R.layout.adapter_project_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //年化利率
        holder.textInterest.setText(String.valueOf(oneitem.getInterest()));
        //项目期限
        holder.textDealline.setText(String.valueOf(oneitem.getDeadline()));
        //项目类型
        if (1 == oneitem.getType()) {
            //普通定期项目
            holder.imgProjectimg.setVisibility(View.VISIBLE);
        } else {
            holder.imgProjectimg.setVisibility(View.GONE);
        }
        //项目名称
        holder.textProjectname.setText(oneitem.getProjectname());

        //项目状态
        switch (oneitem.getStatus()) {
            case 1:
                //正在进行
                holder.relStatus.setBackgroundResource(R.drawable.project_status_online);
                holder.textProjecttime.setVisibility(View.GONE);
                holder.textProjectstatus.setVisibility(View.VISIBLE);
                holder.textProjectstatus.setText("转入");
                break;
            case 2:
                //已满标
                holder.relStatus.setBackgroundResource(R.drawable.project_status_full);
                holder.textProjecttime.setVisibility(View.GONE);
                holder.textProjectstatus.setVisibility(View.VISIBLE);
                holder.textProjectstatus.setText("满标");
                break;
            case 3:
                //还款中
                holder.relStatus.setBackgroundResource(R.drawable.project_status_full);
                holder.textProjecttime.setVisibility(View.GONE);
                holder.textProjectstatus.setVisibility(View.VISIBLE);
                holder.textProjectstatus.setText("还款中");
                break;
            case 4:
                //未开标
                holder.relStatus.setBackgroundResource(R.drawable.project_status_full);
                holder.textProjecttime.setVisibility(View.VISIBLE);
                holder.textProjectstatus.setVisibility(View.GONE);
                holder.textProjecttime.start(oneitem.getCountdowntime());
                break;
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.text_interest)
        TextView textInterest;
        @BindView(R.id.lin_interest)
        LinearLayout linInterest;
        @BindView(R.id.text_dealline)
        TextView textDealline;
        @BindView(R.id.text_projectstatus)
        TextView textProjectstatus;
        @BindView(R.id.text_projecttime)
        CountdownView textProjecttime;
        @BindView(R.id.rel_status)
        RelativeLayout relStatus;
        @BindView(R.id.img_projectimg)
        ImageView imgProjectimg;
        @BindView(R.id.text_projectname)
        TextView textProjectname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
