package com.bld.applets.controller;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.service.IAppletsOrderService;
import com.bld.applets.utils.CommonUtils;
import com.bld.applets.utils.ServiceUtil;
import com.bld.applets.wxpay.WxUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsUserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询用户列表
     *
     */
    @GetMapping("/get")
    @ResponseBody
    public AppletsUser get()
    {
        AppletsUser user = CommonUtils.getUser();
        // 查询本月充电量
        Long thisMonthCharge = appletsUserService.getThisMonthCharge(user.getId());
        // 计算会员等级差
        Long memberDifference = ServiceUtil.getMemberDifference();
        user.setMemberDifference(memberDifference).setMonthCharge(thisMonthCharge);
        return CommonUtils.getUser();
    }

    /**
     * 新增保存用户
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsUser appletsUser)
    {
        // 查重
        List<AppletsUser> appletsUsers = appletsUserService.selectAppletsUserList(appletsUser);
        if (!appletsUsers.isEmpty()) {
            return AjaxResult.error("该账号已存在");
        }
        appletsUser.setCreateTime(new Date());
        appletsUser.setLevel(1)
                .setIntegral(0l)
                .setBalance("0")
                .setOwner(0)
                .setMonthCharge(0l)
                .setMemberDifference(0l);
        int result = appletsUserService.insertAppletsUser(appletsUser);
        // 操作成功
        if (result > 0) {
            // 用户信息存入session
            CommonUtils.getSession().setAttribute("user", appletsUser);
        }
        return toAjax(result);
    }

    /**
     * 修改保存用户
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsUser appletsUser)
    {
        appletsUser.setUpdateTime(new Date());
        return toAjax(appletsUserService.updateAppletsUser(appletsUser.setId(CommonUtils.getUserId())));
    }

    /**
     * 删除用户
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove()
    {
        int result = appletsUserService.deleteAppletsUserByIds(String.valueOf(CommonUtils.getUserId()));
        // 删除成功，移除session信息
        if (result > 0) {
            CommonUtils.getSession().removeAttribute("user");
        }
        return toAjax(result);
    }

    /**
     * 查询订单列表
     */
    @GetMapping("/getBalance")
    @ResponseBody
    public TableDataInfo getBalance()
    {
        startPage();
        List<AppletsOrder> appletsOrders = orderService.selectCompletOrder(CommonUtils.getUserId());
        return getDataTable(appletsOrders);
    }

    /**
     * 用户登录
     */
    @GetMapping( "/signIn")
    @ResponseBody
    public AjaxResult signIn(String code) {
        if (StringUtils.isEmpty(code)) {
            return AjaxResult.error("请求参数缺失");
        }
        // 获取openId
        Map<String, Object> result = WxUtils.code2Session(code);
        Object openid = result.get("openid");
        if (openid == null) {
            return AjaxResult.error(result.get("errcode") + " : " + result.get("errmsg"));
        }
        AppletsUser appletsUser = new AppletsUser().setCode(openid.toString());
        List<AppletsUser> appletsUsers = appletsUserService.selectAppletsUserList(appletsUser);
        if (CollectionUtils.isEmpty(appletsUsers)) {
            return AjaxResult.error("未查询到用户信息").put("data", openid.toString());
        } else {
            CommonUtils.getRequest().getSession().setAttribute("user", appletsUsers.get(0));
            return AjaxResult.success(appletsUsers.get(0));
        }
    }

    /**
     * 用户注册
     */
    @PostMapping( "/signUp")
    @ResponseBody
    public AjaxResult signUp(AppletsUser user) {
        // 查重
        List<AppletsUser> appletsUsers = appletsUserService.selectAppletsUserList(user);
        // 账号已存在
        if (!appletsUsers.isEmpty()) {
            CommonUtils.getRequest().getSession().setAttribute("user", appletsUsers.get(0));
            return AjaxResult.success(appletsUsers.get(0));
        }
        user.setLevel(1)
                .setOwner(0)
                .setIntegral(0l)
                .setBalance("0")
                .setMonthCharge(0l)
                .setMemberDifference(0l)
                .setCreateTime(new Date())
                .setUpdateTime(new Date());
        int result = appletsUserService.insertAppletsUser(user);
        // 成功
        if (result > 0) {
            CommonUtils.getRequest().getSession().setAttribute("user", user);
            return AjaxResult.success(user);
        }
        return toAjax(result);

    }

}
