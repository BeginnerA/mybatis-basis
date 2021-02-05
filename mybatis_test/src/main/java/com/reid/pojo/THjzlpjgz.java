package com.reid.pojo;

import java.io.Serializable;

public class THjzlpjgz {
    /**
    * ID
    */
    private String id;
    /**
    * 用户ID
    */
    private String userid;
    /**
    * 评价标准代码
    */
    private String bzdm;
    /**
    * 评价标准名称
    */
    private String bzmc;
    /**
    * 评价等级
    */
    private String pjdj;
    /**
    * 评价结果
    */
    private String pjjg;
    /**
    * Pip最小值
    */
    private Double pipMin;
    /**
    * Pip最大值
    */
    private Double pipMax;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBzdm() {
        return bzdm;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public String getBzmc() {
        return bzmc;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public String getPjdj() {
        return pjdj;
    }

    public void setPjdj(String pjdj) {
        this.pjdj = pjdj;
    }

    public String getPjjg() {
        return pjjg;
    }

    public void setPjjg(String pjjg) {
        this.pjjg = pjjg;
    }

    public Double getPipMin() {
        return pipMin;
    }

    public void setPipMin(Double pipMin) {
        this.pipMin = pipMin;
    }

    public Double getPipMax() {
        return pipMax;
    }

    public void setPipMax(Double pipMax) {
        this.pipMax = pipMax;
    }

    @Override
    public String toString() {
        return "THjzlpjgz{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", bzdm='" + bzdm + '\'' +
                ", bzmc='" + bzmc + '\'' +
                ", pjdj='" + pjdj + '\'' +
                ", pjjg='" + pjjg + '\'' +
                ", pipMin=" + pipMin +
                ", pipMax=" + pipMax +
                '}';
    }
}