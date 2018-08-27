package com.imooc.oa.entity;

import javax.persistence.*;

@Entity
@Table(name = "claim_voucher_item", schema = "oa")
public class ClaimVoucherItem {
    private int id;
    private Integer claimVoucherId;
    private String item;
    private Double amount;
    private String comment;

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
    @Column(name = "item")
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Basic
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

        ClaimVoucherItem that = (ClaimVoucherItem) o;

        if (id != that.id) return false;
        if (claimVoucherId != null ? !claimVoucherId.equals(that.claimVoucherId) : that.claimVoucherId != null)
            return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (claimVoucherId != null ? claimVoucherId.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}