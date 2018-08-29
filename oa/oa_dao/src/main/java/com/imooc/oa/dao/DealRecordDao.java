package com.imooc.oa.dao;

import com.imooc.oa.entity.DealRecord;

import java.util.List;

public interface DealRecordDao {
    void insert(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
