package com.bld.applets.controller;

import java.util.List;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsPower;
import com.bld.applets.service.IAppletsPowerService;

/**
 * 功率图Controller
 * 
 * @author tyx
 * @date 2021-04-12
 */
@Controller
@RequestMapping("/applets/power")
public class AppletsPowerController extends BaseController
{
    private String prefix = "applets/power";

    @Autowired
    private IAppletsPowerService appletsPowerService;

    /**
     * 查询功率图列表
     */
    @GetMapping("/getTodayList")
    @ResponseBody
    public TableDataInfo getTodayList(AppletsPower appletsPower)
    {
        startPage();
        List<AppletsPower> list = appletsPowerService.selectTodayPowerList(appletsPower);
        return getDataTable(list);
    }

    /**
     * 查询功率图列表
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsPower appletsPower)
    {
        startPage();
        List<AppletsPower> list = appletsPowerService.selectAppletsPowerList(appletsPower);
        return getDataTable(list);
    }

    /**
     * 新增保存功率图
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsPower appletsPower)
    {
        return toAjax(appletsPowerService.insertAppletsPower(appletsPower));
    }

    /**
     * 修改保存功率图
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsPower appletsPower)
    {
        return toAjax(appletsPowerService.updateAppletsPower(appletsPower));
    }

    /**
     * 删除功率图
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsPowerService.deleteAppletsPowerByIds(ids));
    }
}
