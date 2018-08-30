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
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);
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

    /**
     * 根据id获取报销单
     * @param id
     * @return
     */
    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    /**
     * 根据id获取报销单条目
     * @param id
     * @return
     */
    public List<ClaimVoucherItem> getItems(int id) {
        return claimVoucherItemDao.selectByClaimVoucher(id);
    }

    /**
     * 根据id获取报销单处理记录
     * @param id
     * @return
     */
    public List<DealRecord> getRecords(int id) {
        return dealRecordDao.selectByClaimVoucher(id);
    }

    /**
     * 修改报销单
     * @param claimVoucher
     * @param items
     */
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        // 更新报销单
        claimVoucherDao.update(claimVoucher);
        // 查询旧条目
        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        // 遍历旧条目
        for (ClaimVoucherItem old:olds) {
            boolean has = false;
            for (ClaimVoucherItem item:items) {
                if (old.getId() == item.getId()) {
                    has = true;
                    break;
                }
            }
            if (has == false)
                // 删除旧条目
                claimVoucherItemDao.delete(old.getId());
        }

        for (ClaimVoucherItem item:items) {
            if (item.getId() > 0) {
                // 修改条目
                claimVoucherItemDao.update(item);
            } else {
                // 增加条目
                claimVoucherItemDao.insert(item);
            }
        }

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