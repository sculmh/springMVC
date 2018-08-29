package com.imooc.oa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


public class ClaimVoucher {
    private int id;
    private String cause;
    private String createSn;
    private Date createTime;
    private String nextDealSn;
    private Double totalAmount;
    private String status;
    private Employee creater;       // 创建人

    public Employee getCreater() {
        return creater;
    }

    public void setCreater(Employee creater) {
        this.creater = creater;
    }

    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }

    private Employee dealer;       // 处理人


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cause")
    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Basic
    @Column(name = "create_sn")
    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "next_deal_sn")
    public String getNextDealSn() {
        return nextDealSn;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn;
    }

    @Basic
    @Column(name = "total_amount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClaimVoucher that = (ClaimVoucher) o;

        if (id != that.id) return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;
        if (createSn != null ? !createSn.equals(that.createSn) : that.createSn != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (nextDealSn != null ? !nextDealSn.equals(that.nextDealSn) : that.nextDealSn != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        result = 31 * result + (createSn != null ? createSn.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (nextDealSn != null ? nextDealSn.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}