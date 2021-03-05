package com.bld.applets.controller;

import java.util.Date;
import java.util.List;

import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.service.IAppletsOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
     * 导出订单列表
     */
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsOrder appletsOrder)
    {
        List<AppletsOrder> list = appletsOrderService.selectAppletsOrderList(appletsOrder);
        ExcelUtil<AppletsOrder> util = new ExcelUtil<AppletsOrder>(AppletsOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增保存订单
     */
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsOrder appletsOrder)
    {
        appletsOrder.setCode(CommonUtils.generateOrderCode())
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
    @Log(title = "订单", businessType = BusinessType.INSERT)
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
                    .setCost(CommonUtils.getCost())
                    // 计算积分
                    .setIntegral(CommonUtils.getIntegral())
                    // 计算充电量
                    .setCharge(CommonUtils.getCharge());
        }
        return toAjax(appletsOrderService.insertAppletsOrder(appletsOrder));
    }

    /**
     * 修改保存订单
     */
    @Log(title = "订单", businessType = BusinessType.UPDATE)
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
    @Log(title = "订单", businessType = BusinessType.DELETE)
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
