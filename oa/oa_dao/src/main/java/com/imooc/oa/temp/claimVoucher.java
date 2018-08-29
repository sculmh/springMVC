package main.java.com.imooc.oa.temp;

import java.util.Date;

public class claimVoucher {
    private Integer id;

    private String cause;

    private String createSn;

    private Date createTime;

    private String nextDealSn;

    private Double totalAmount;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn == null ? null : createSn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextDealSn() {
        return nextDealSn;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn == null ? null : nextDealSn.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}