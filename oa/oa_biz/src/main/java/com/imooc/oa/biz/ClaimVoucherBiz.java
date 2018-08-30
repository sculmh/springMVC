package com.imooc.oa.biz;

import com.imooc.oa.entity.ClaimVoucher;
import com.imooc.oa.entity.ClaimVoucherItem;
import com.imooc.oa.entity.DealRecord;

import java.util.List;

/**
 * 报销单业务层接口
 */
public interface ClaimVoucherBiz {
    // 保存报销单
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    // 根据id获取报销单
    ClaimVoucher get(int id);
    // 根据id获取报销详细条目
    List<ClaimVoucherItem> getItems(int id);
    // 根据id获取报销单处理记录
    List<DealRecord> getRecords(int id);
    // 修改报销单
    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    // 获取创建者的报销单
    List<ClaimVoucher> getForSelf(String sn);
    // 获取待处理者的报销单
    List<ClaimVoucher> getForDeal(String sn);
    // 提交报销单
    void submit(int id);
    // 审核报销单
    void deal(DealRecord dealRecord);
}
