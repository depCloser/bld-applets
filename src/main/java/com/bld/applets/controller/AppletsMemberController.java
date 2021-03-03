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
import com.bld.applets.domain.AppletsMember;
import com.bld.applets.service.IAppletsMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员等级配置Controller
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/member")
public class AppletsMemberController extends BaseController
{

    @Autowired
    private IAppletsMemberService appletsMemberService;

    /**
     * 查询会员等级配置列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsMember appletsMember)
    {
        startPage();
        List<AppletsMember> list = appletsMemberService.selectAppletsMemberList(appletsMember);
        return getDataTable(list);
    }

    /**
     * 导出会员等级配置列表
     */
    @Log(title = "会员等级配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsMember appletsMember)
    {
        List<AppletsMember> list = appletsMemberService.selectAppletsMemberList(appletsMember);
        ExcelUtil<AppletsMember> util = new ExcelUtil<AppletsMember>(AppletsMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增保存会员等级配置
     */
    @Log(title = "会员等级配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsMember appletsMember)
    {
        return toAjax(appletsMemberService.insertAppletsMember(appletsMember));
    }

    /**
     * 修改保存会员等级配置
     */
    @Log(title = "会员等级配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsMember appletsMember)
    {
        return toAjax(appletsMemberService.updateAppletsMember(appletsMember));
    }

    /**
     * 删除会员等级配置
     */
    @Log(title = "会员等级配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsMemberService.deleteAppletsMemberByIds(ids));
    }
}
