package com.test;

import java.util.Date;

/**
 * 业务类
 * 
 * @author andy
 * @date 2016年11月15日
 */
public class ChannelSituationBean implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5754354214462805574L;

    private Integer id;
    private Date date;
    private int registCount;
    private Float fee;
    private float myFee;
    private Double investMoney;
    private double myInvestMoney;
    private String registChannel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRegistCount() {
        return registCount;
    }

    public void setRegistCount(int registCount) {
        this.registCount = registCount;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Double getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(Double investMoney) {
        this.investMoney = investMoney;
    }

    public String getRegistChannel() {
        return registChannel;
    }

    public void setRegistChannel(String registChannel) {
        this.registChannel = registChannel;
    }

    public float getMyFee() {
        return myFee;
    }

    public void setMyFee(float myFee) {
        this.myFee = myFee;
    }

    public double getMyInvestMoney() {
        return myInvestMoney;
    }

    public void setMyInvestMoney(double myInvestMoney) {
        this.myInvestMoney = myInvestMoney;
    }

}
