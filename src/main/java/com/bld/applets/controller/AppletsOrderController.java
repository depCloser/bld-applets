package com.bld.applets.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
 * 
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
}
