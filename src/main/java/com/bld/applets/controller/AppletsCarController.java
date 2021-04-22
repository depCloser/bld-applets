package com.bld.applets.controller;

import java.util.Date;
import java.util.List;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsCar;
import com.bld.applets.service.IAppletsCarService;

/**
 * 用户车辆Controller
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
    @GetMapping("/list")
    @ResponseBody
    public Object list(AppletsCar appletsCar)
    {
        startPage();
        List<AppletsCar> list = appletsCarService.selectAppletsCarList(appletsCar.setUserId(CommonUtils.getUserId()));
        return getDataTable(list);
    }

    /**
     * 新增保存用户车辆
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsCar appletsCar)
    {
        appletsCar.setCreateTime(new Date()).setUpdateTime(new Date());
        return toAjax(appletsCarService.insertAppletsCar(appletsCar.setUserId(CommonUtils.getUserId())));
    }

    /**
     * 修改保存用户车辆
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsCar appletsCar)
    {
        appletsCar.setUpdateTime(new Date());
        return toAjax(appletsCarService.updateAppletsCar(appletsCar));
    }

    /**
     * 删除用户车辆
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsCarService.deleteAppletsCarByIds(ids));
    }
}
