package com.imooc.oa.dao;

import com.imooc.oa.entity.ClaimVoucher;

import java.util.List;

public interface ClaimVoucherDao {

    void insert(ClaimVoucher claimVoucher);

    void update(ClaimVoucher record);

    void delete(int id);

    ClaimVoucher select(int id);

    List<ClaimVoucher> selectByCreateSn(String sn);

    List<ClaimVoucher> selectByNextDealSn(String sn);

}