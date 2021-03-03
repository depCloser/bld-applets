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
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.service.IAppletsPilesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电桩Controller
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/piles")
public class AppletsPilesController extends BaseController
{

    @Autowired
    private IAppletsPilesService appletsPilesService;

    /**
     * 查询充电桩列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsPiles appletsPiles)
    {
        startPage();
        List<AppletsPiles> list = appletsPilesService.selectAppletsPilesList(appletsPiles);
        return getDataTable(list);
    }

    /**
     * 导出充电桩列表
     */
    @Log(title = "充电桩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsPiles appletsPiles)
    {
        List<AppletsPiles> list = appletsPilesService.selectAppletsPilesList(appletsPiles);
        ExcelUtil<AppletsPiles> util = new ExcelUtil<AppletsPiles>(AppletsPiles.class);
        return util.exportExcel(list, "piles");
    }

    /**
     * 新增保存充电桩
     */
    @Log(title = "充电桩", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsPiles appletsPiles)
    {
        return toAjax(appletsPilesService.insertAppletsPiles(appletsPiles));
    }

    /**
     * 修改保存充电桩
     */
    @Log(title = "充电桩", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsPiles appletsPiles)
    {
        return toAjax(appletsPilesService.updateAppletsPiles(appletsPiles));
    }

    /**
     * 删除充电桩
     */
    @Log(title = "充电桩", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsPilesService.deleteAppletsPilesByIds(ids));
    }
}