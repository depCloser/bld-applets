package com.bld.applets.controller;

import java.util.List;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.service.IAppletsPilesService;

/**
 * 充电桩Controller
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
        List<AppletsPiles> list = appletsPilesService.selectAppletsPilesList(appletsPiles.setUserId(CommonUtils.getUserId()));
        return getDataTable(list);
    }

    /**
     * 新增保存充电桩
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsPiles appletsPiles)
    {
        return toAjax(appletsPilesService.insertAppletsPiles(appletsPiles.setUserId(CommonUtils.getUserId())));
    }

    /**
     * 修改保存充电桩
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsPiles appletsPiles)
    {
        return toAjax(appletsPilesService.updateAppletsPiles(appletsPiles));
    }

    /**
     * 删除充电桩
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsPilesService.deleteAppletsPilesByIds(ids));
    }
}
