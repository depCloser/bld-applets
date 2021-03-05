package com.bld.applets.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.bld.applets.domain.AppletsElectrovalence;
import com.bld.applets.service.IAppletsElectrovalenceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 充电桩电价Controller
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
    public Map list(AppletsElectrovalence appletsElectrovalence)
    {
        List<AppletsElectrovalence> list = appletsElectrovalenceService.selectAppletsElectrovalenceList(appletsElectrovalence.setUserId(CommonUtils.getUserId()));
        Map<AppletsPiles, List<AppletsElectrovalence>> result = list.stream().collect(Collectors.groupingBy(AppletsElectrovalence::getPiles));
        return result;
    }

    /**
     * 新增保存充电桩电价
     */
    @Log(title = "充电桩电价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsElectrovalence appletsElectrovalence)
    {
        return toAjax(appletsElectrovalenceService.insertAppletsElectrovalence(appletsElectrovalence.setUserId(CommonUtils.getUserId())));
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
