package com.imooc.oa.dao;

import com.imooc.oa.entity.ClaimVoucher;
import main.java.com.imooc.oa.temp.claimVoucher;

import java.util.List;

public interface claimVoucherDao {

    void insert(claimVoucher record);

    void update(claimVoucher record);

    void delete(int id);

    ClaimVoucher select(int id);

    List<ClaimVoucher> selectByCreateSn(String sn);

    List<ClaimVoucher> selectByNextDealSn(String sn);

}