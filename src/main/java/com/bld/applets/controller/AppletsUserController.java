package com.bld.applets.controller;

import com.bld.applets.service.IAppletsConfigService;
import com.bld.applets.service.IAppletsOrderService;
import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

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

    @Autowired
    private IAppletsOrderService orderService;

    @Autowired
    private IAppletsConfigService configService;

    /**
     * 查询用户列表
     *
     */
    @PostMapping("/get")
    @ResponseBody
    public AppletsUser get()
    {
        AppletsUser user = CommonUtils.getUser();
        // 查询本月充电量
        Long thisMonthCharge = appletsUserService.getThisMonthCharge();
        // 计算会员等级差
        Long memberDifference = CommonUtils.getMemberDifference();
        user.setMemberDifference(memberDifference).setMonthCharge(thisMonthCharge);
        return CommonUtils.getUser();
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
        AppletsUser user = CommonUtils.getUser();
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
