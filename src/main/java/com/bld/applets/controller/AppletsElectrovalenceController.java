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
import com.bld.applets.domain.AppletsElectrovalence;
import com.bld.applets.service.IAppletsElectrovalenceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电桩电价Controller
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/electrovalence")
public class AppletsElectrovalenceController extends BaseController
{

    @Autowired
    private IAppletsElectrovalenceService appletsElectrovalenceService;

    /**
     * 查询充电桩电价列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsElectrovalence appletsElectrovalence)
    {
        startPage();
        List<AppletsElectrovalence> list = appletsElectrovalenceService.selectAppletsElectrovalenceList(appletsElectrovalence);
        return getDataTable(list);
    }

    /**
     * 导出充电桩电价列表
     */
    @Log(title = "充电桩电价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsElectrovalence appletsElectrovalence)
    {
        List<AppletsElectrovalence> list = appletsElectrovalenceService.selectAppletsElectrovalenceList(appletsElectrovalence);
        ExcelUtil<AppletsElectrovalence> util = new ExcelUtil<AppletsElectrovalence>(AppletsElectrovalence.class);
        return util.exportExcel(list, "electrovalence");
    }

    /**
     * 新增保存充电桩电价
     */
    @Log(title = "充电桩电价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsElectrovalence appletsElectrovalence)
    {
        return toAjax(appletsElectrovalenceService.insertAppletsElectrovalence(appletsElectrovalence));
    }

    /**
     * 修改保存充电桩电价
     */
    @Log(title = "充电桩电价", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsElectrovalence appletsElectrovalence)
    {
        return toAjax(appletsElectrovalenceService.updateAppletsElectrovalence(appletsElectrovalence));
    }

    /**
     * 删除充电桩电价
     */
    @Log(title = "充电桩电价", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsElectrovalenceService.deleteAppletsElectrovalenceByIds(ids));
    }
}
