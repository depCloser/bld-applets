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
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/user")
public class AppletsUserController extends BaseController
{

    @Autowired
    private IAppletsUserService appletsUserService;

    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsUser appletsUser)
    {
        startPage();
        List<AppletsUser> list = appletsUserService.selectAppletsUserList(appletsUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppletsUser appletsUser)
    {
        List<AppletsUser> list = appletsUserService.selectAppletsUserList(appletsUser);
        ExcelUtil<AppletsUser> util = new ExcelUtil<AppletsUser>(AppletsUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增保存用户
     */
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsUser appletsUser)
    {
        return toAjax(appletsUserService.insertAppletsUser(appletsUser));
    }

    /**
     * 修改保存用户
     */
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsUser appletsUser)
    {
        return toAjax(appletsUserService.updateAppletsUser(appletsUser));
    }

    /**
     * 删除用户
     */
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsUserService.deleteAppletsUserByIds(ids));
    }
}
