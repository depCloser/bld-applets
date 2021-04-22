package com.bld.applets.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsCollect;
import com.bld.applets.service.IAppletsCollectService;

/**
 * 用户收藏 Controller
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
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsCollect appletsCollect)
    {
        startPage();
        List<AppletsCollect> list = appletsCollectService.selectAppletsCollectList(appletsCollect.setUserId(CommonUtils.getUserId()));
        List<AppletsPiles> pilesList = list.stream().map(e -> {return e.getPiles();}).collect(Collectors.toList());
        return getDataTable(pilesList);
    }

    /**
     * 新增保存用户收藏（用户-充电桩关联，多对多）
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsCollect appletsCollect)
    {
        appletsCollect.setCreateTime(new Date()).setUpdateTime(new Date());
        return toAjax(appletsCollectService.insertAppletsCollect(appletsCollect.setUserId(CommonUtils.getUserId())));
    }

    /**
     * 修改保存用户收藏（用户-充电桩关联，多对多）
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsCollect appletsCollect)
    {
        appletsCollect.setUpdateTime(new Date());
        return toAjax(appletsCollectService.updateAppletsCollect(appletsCollect));
    }

    /**
     * 删除用户收藏（用户-充电桩关联，多对多）
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsCollectService.deleteAppletsCollectByIds(ids));
    }
}
