package com.bld.applets.controller;

import java.util.Date;
import java.util.List;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.utils.CommonUtils;
import com.bld.applets.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.service.IAppletsOrderService;

/**
 * 订单Controller
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/order")
public class AppletsOrderController extends BaseController
{

    @Autowired
    private IAppletsOrderService appletsOrderService;

    /**
     * 查询订单列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsOrder appletsOrder)
    {
        startPage();
        List<AppletsOrder> list = appletsOrderService.selectAppletsOrderList(appletsOrder);
        return getDataTable(list);
    }

    /**
     * 新增保存订单
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsOrder appletsOrder)
    {
        appletsOrder.setCode(ServiceUtil.generateOrderCode())
                .setUserId(CommonUtils.getUserId())
                .setDate(new Date())
                .setStatus(0);
        return toAjax(appletsOrderService.insertAppletsOrder(appletsOrder));
    }

    /*
     * @Author: tyx
     * @Description: 开始/结束充电
     * @Param: [orderId, type]->[订单号，类型：1开始充电， 0结束充电]
     * @return: com.ruoyi.common.core.domain.AjaxResult
     * @Date: 2021/3/3
     */
    @PostMapping("/recharge")
    @ResponseBody
    public AjaxResult recharge(Long orderId, Integer type)
    {
        AppletsOrder appletsOrder = new AppletsOrder().setId(orderId);
        // 开始充电
        if (0 != type) {
            appletsOrder.setStartTime(new Date());
        } else { // 充电结束，进行条目结算
            // 暂无计算公式
            appletsOrder.setEndTime(new Date())
                    // 计算费用
                    .setCost(ServiceUtil.getCost())
                    // 计算积分
                    .setIntegral(ServiceUtil.getIntegral())
                    // 计算充电量
                    .setCharge(ServiceUtil.getCharge());
        }
        return toAjax(appletsOrderService.insertAppletsOrder(appletsOrder));
    }

    /**
     * 修改保存订单
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsOrder appletsOrder)
    {
        // 621723 3100010488873
        return toAjax(appletsOrderService.updateAppletsOrder(appletsOrder));
    }

    /**
     * 删除订单
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsOrderService.deleteAppletsOrderByIds(ids));
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
    }

}
