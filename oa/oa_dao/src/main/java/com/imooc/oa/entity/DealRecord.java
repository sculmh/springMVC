package com.imooc.oa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


public class DealRecord {
    private int id;
    private Integer claimVoucherId;
    private String dealSn;
    private Date dealTime;
    private String dealWay;
    private String dealResult;
    private String comment;
    private Employee dealer;    // 处理人

    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "claim_voucher_id")
    public Integer getClaimVoucherId() {
        return claimVoucherId;
    }

    public void setClaimVoucherId(Integer claimVoucherId) {
        this.claimVoucherId = claimVoucherId;
    }

    @Basic
    @Column(name = "deal_sn")
    public String getDealSn() {
        return dealSn;
    }

    public void setDealSn(String dealSn) {
        this.dealSn = dealSn;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    @Basic
    @Column(name = "deal_way")
    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    @Basic
    @Column(name = "deal_result")
    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DealRecord that = (DealRecord) o;

        if (id != that.id) return false;
        if (claimVoucherId != null ? !claimVoucherId.equals(that.claimVoucherId) : that.claimVoucherId != null)
            return false;
        if (dealSn != null ? !dealSn.equals(that.dealSn) : that.dealSn != null) return false;
        if (dealTime != null ? !dealTime.equals(that.dealTime) : that.dealTime != null) return false;
        if (dealWay != null ? !dealWay.equals(that.dealWay) : that.dealWay != null) return false;
        if (dealResult != null ? !dealResult.equals(that.dealResult) : that.dealResult != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (claimVoucherId != null ? claimVoucherId.hashCode() : 0);
        result = 31 * result + (dealSn != null ? dealSn.hashCode() : 0);
        result = 31 * result + (dealTime != null ? dealTime.hashCode() : 0);
        result = 31 * result + (dealWay != null ? dealWay.hashCode() : 0);
        result = 31 * result + (dealResult != null ? dealResult.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}