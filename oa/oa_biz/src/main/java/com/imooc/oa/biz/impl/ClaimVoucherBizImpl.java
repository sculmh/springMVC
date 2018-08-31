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

        int id = claimVoucher.getId();

        for (ClaimVoucherItem item:items) {
            if (item.getId() > 0) {
                // 修改条目
                claimVoucherItemDao.update(item);
            } else {
                // 增加条目
                item.setClaimVoucherId(id);
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

    /**
     * 提交报销单
     * @param id
     */
    public void submit(int id) {
        // 获取对应报销单
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        // 获取报销单创建者
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());

        // 设置报销单为已提交状态
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_SUBMIT);
        // 设置待处理人
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(),Constant.POST_FM).get(0).getSn());
        // 更新报销单
        claimVoucherDao.update(claimVoucher);

        // 报销单处理记录
        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Constant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Constant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        // 保存记录
        dealRecordDao.insert(dealRecord);
    }

    /**
     * 审核报销单
     * @param dealRecord
     */
    public void deal(DealRecord dealRecord) {
        // 获取报销单
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        Employee employee = employeeDao.select(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());

        // 处理方式
        String dealWay = dealRecord.getDealWay();

        if (Constant.DEAL_PASS.equals(dealWay)) {
            // 通过
            if (claimVoucher.getTotalAmount() <= Constant.LIMIT_CHECK
            || employee.getPost().equals(Constant.POST_GM) ) {
                // 不需要复审
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_CASHIER).get(0).getSn());

                dealRecord.setDealResult(Constant.CLAIMVOUCHER_APPROVED);
            } else {
                // 需要复审
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Constant.CLAIMVOUCHER_RECHECK);
            }

        } else if (Constant.DEAL_BACK.equals(dealWay)) {
            // 打回
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Constant.CLAIMVOUCHER_BACK);
        } else if (Constant.DEAL_REJECT.equals(dealWay)) {
            // 拒绝
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Constant.CLAIMVOUCHER_TERMINATED);
        } else if (Constant.DEAL_PAID.equals(dealWay)) {
            // 打款
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Constant.CLAIMVOUCHER_PAID);
        }
        // 更新数据库
        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }
}