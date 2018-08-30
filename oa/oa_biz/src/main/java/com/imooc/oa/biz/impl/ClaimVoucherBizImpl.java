package com.imooc.oa.biz.impl;

import com.imooc.oa.biz.ClaimVoucherBiz;
import com.imooc.oa.dao.ClaimVoucherDao;
import com.imooc.oa.dao.ClaimVoucherItemDao;
import com.imooc.oa.dao.DealRecordDao;
import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.entity.ClaimVoucher;
import com.imooc.oa.entity.ClaimVoucherItem;
import com.imooc.oa.entity.DealRecord;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.global.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 报销单处理业务层实现
 */
@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

    @Autowired
    private ClaimVoucherDao claimVoucherDao;

    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;

    @Autowired
    private DealRecordDao dealRecordDao;

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 保存报销单
     * @param claimVoucher
     * @param items
     */
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        // 设置报销单具体信息
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.DEAL_CREATE);
        // 保存
        claimVoucherDao.insert(claimVoucher);
        // 获取报销单编号
        int id = claimVoucher.getId();

        // 遍历报销单条目
        for (ClaimVoucherItem item: items) {
            item.setClaimVoucherId(id);
            claimVoucherItemDao.insert(item);
        }

    }

    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(int id) {
        return claimVoucherItemDao.selectByClaimVoucher(id);
    }

    public List<DealRecord> getRecords(int id) {
        return dealRecordDao.selectByClaimVoucher(id);
    }

    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {

    }

    /**
     * 获取创建者的报销单
     * @param sn
     * @return
     */
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    /**
     * 获取待处理者的报销单
     * @param sn
     * @return
     */
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }
}