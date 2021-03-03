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
import com.bld.applets.domain.AppletsCollect;
import com.bld.applets.service.IAppletsCollectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户收藏（用户-充电桩关联，多对多）Controller
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/collect")
public class AppletsCollectController extends BaseController
{

    @Autowired
    private IAppletsCollectService appletsCollectService;

    /**
     * 查询用户收藏（用户-充电桩关联，多对多）列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsCollect appletsCollect)
    {
        startPage();
        List<AppletsCollect> list = appletsCollectService.selectAppletsCollectList(appletsCollect);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏（用户-充电桩关联，多对多）列表
     */
    @Log(title = "用户收藏（用户-充电桩关联，多对多）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsCollect appletsCollect)
    {
        List<AppletsCollect> list = appletsCollectService.selectAppletsCollectList(appletsCollect);
        ExcelUtil<AppletsCollect> util = new ExcelUtil<AppletsCollect>(AppletsCollect.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 新增保存用户收藏（用户-充电桩关联，多对多）
     */
    @Log(title = "用户收藏（用户-充电桩关联，多对多）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsCollect appletsCollect)
    {
        return toAjax(appletsCollectService.insertAppletsCollect(appletsCollect));
    }

    /**
     * 修改保存用户收藏（用户-充电桩关联，多对多）
     */
    @Log(title = "用户收藏（用户-充电桩关联，多对多）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsCollect appletsCollect)
    {
        return toAjax(appletsCollectService.updateAppletsCollect(appletsCollect));
    }

    /**
     * 删除用户收藏（用户-充电桩关联，多对多）
     */
    @Log(title = "用户收藏（用户-充电桩关联，多对多）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsCollectService.deleteAppletsCollectByIds(ids));
    }
}
