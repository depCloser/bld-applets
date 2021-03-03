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
import com.bld.applets.domain.AppletsCar;
import com.bld.applets.service.IAppletsCarService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户车辆Controller
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/car")
public class AppletsCarController extends BaseController
{

    @Autowired
    private IAppletsCarService appletsCarService;

    /**
     * 查询用户车辆列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsCar appletsCar)
    {
        startPage();
        List<AppletsCar> list = appletsCarService.selectAppletsCarList(appletsCar);
        return getDataTable(list);
    }

    /**
     * 导出用户车辆列表
     */
    @Log(title = "用户车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsCar appletsCar)
    {
        List<AppletsCar> list = appletsCarService.selectAppletsCarList(appletsCar);
        ExcelUtil<AppletsCar> util = new ExcelUtil<AppletsCar>(AppletsCar.class);
        return util.exportExcel(list, "car");
    }

    /**
     * 新增保存用户车辆
     */
    @Log(title = "用户车辆", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsCar appletsCar)
    {
        return toAjax(appletsCarService.insertAppletsCar(appletsCar));
    }

    /**
     * 修改保存用户车辆
     */
    @Log(title = "用户车辆", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsCar appletsCar)
    {
        return toAjax(appletsCarService.updateAppletsCar(appletsCar));
    }

    /**
     * 删除用户车辆
     */
    @Log(title = "用户车辆", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsCarService.deleteAppletsCarByIds(ids));
    }
}
