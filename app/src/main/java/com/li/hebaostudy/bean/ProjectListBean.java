package com.li.hebaostudy.bean;

import java.io.Serializable;

/**
  *  @copyright:北京爱钱帮财富科技有限公司
  *  功能描述: 标地列表的bean
  *   作 者:  李晓楠
  *   时 间： 2017/1/10 9:04 
 */
public class ProjectListBean implements Serializable{
    private int id;//项目id
    private int type;//项目类型     1、普通定期项目 2 普通项目
    private double interest;//年化利率
    private int deadline;//项目时间
    private int status;//项目状态   1、正在进行 2、已满标 3、还款中 4、未开标
    private String projectname;//项目名称
    private long countdowntime;//倒计时
    private int borrowmoney;//融资金额
    private String repaymenttype;//还款方式
    private String starttime;//开始时间
    private String startinterest;//起息日
    private int credit;//信誉值

    public int getBorrowmoney() {
        return borrowmoney;
    }

    public void setBorrowmoney(int borrowmoney) {
        this.borrowmoney = borrowmoney;
    }

    public long getCountdowntime() {
        return countdowntime;
    }

    public void setCountdowntime(long countdowntime) {
        this.countdowntime = countdowntime;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getRepaymenttype() {
        return repaymenttype;
    }

    public void setRepaymenttype(String repaymenttype) {
        this.repaymenttype = repaymenttype;
    }

    public String getStartinterest() {
        return startinterest;
    }

    public void setStartinterest(String startinterest) {
        this.startinterest = startinterest;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
