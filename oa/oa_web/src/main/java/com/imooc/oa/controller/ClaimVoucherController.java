package com.imooc.oa.controller;

import com.imooc.oa.biz.ClaimVoucherBiz;
import com.imooc.oa.dto.ClaimVoucherInfo;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.global.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 报销单处理控制层
 */
@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherBiz claimVoucherBiz;

    /**
     * 请求增加报销单
     */
    @RequestMapping("/to_add")
    public String toAdd(Model model) {
        model.addAttribute("items", Constant.getItems());
        model.addAttribute("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    /**
     * 增加报销单
     */
    @RequestMapping("/add")
    public String add(ClaimVoucherInfo info, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.save(info.getClaimVoucher(),info.getItems());
        return "redirect:detail?id=" + info.getClaimVoucher().getId();
    }

    /**
     * 报销单详情
     */
    @RequestMapping("/detail")
    public String detail(int id,Model model) {
        System.out.println("id=" + id);
        model.addAttribute("claimVoucher",claimVoucherBiz.get(id));
        model.addAttribute("items",claimVoucherBiz.getItems(id));
        model.addAttribute("records",claimVoucherBiz.getRecords(id));
        return "claim_voucher_detail";
    }

    /**
     * 查看自己创建的账单
     */
    @RequestMapping("/self")
    public String self(HttpSession session,Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        // 查询账单
        model.addAttribute("list",claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    /**
     * 查看待处理的账单
     */
    @RequestMapping("/deal")
    public String deal(HttpSession session,Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        // 查询账单
        model.addAttribute("list",claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }


}