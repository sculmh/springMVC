package com.imooc.oa.dao;

import com.imooc.oa.entity.ClaimVoucherItem;

import java.util.List;

public interface ClaimVoucherItemDao {
    void insert(ClaimVoucherItem claimVoucherItem);
    void update(ClaimVoucherItem claimVoucherItem);
    void delete(int id);
    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);
}
