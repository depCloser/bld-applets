package com.bld.applets.controller;

import java.util.List;

import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.bld.applets.domain.AppletsFeedback;
import com.bld.applets.service.IAppletsFeedbackService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 反馈Controller
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/feedback")
public class AppletsFeedbackController extends BaseController
{

    @Autowired
    private IAppletsFeedbackService appletsFeedbackService;

    /**
     * 查询反馈列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsFeedback appletsFeedback)
    {
        startPage();
        List<AppletsFeedback> list = appletsFeedbackService.selectAppletsFeedbackList(appletsFeedback.setUserId(CommonUtils.getUserId()));
        return getDataTable(list);
    }

    /**
     * 新增保存反馈
     */
    @Log(title = "反馈", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsFeedback appletsFeedback)
    {
        return toAjax(appletsFeedbackService.insertAppletsFeedback(appletsFeedback.setUserId(CommonUtils.getUserId())));
    }

    /**
     * 修改保存反馈
     */
    @Log(title = "反馈", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsFeedback appletsFeedback)
    {
        return toAjax(appletsFeedbackService.updateAppletsFeedback(appletsFeedback));
    }

    /**
     * 删除反馈
     */
    @Log(title = "反馈", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsFeedbackService.deleteAppletsFeedbackByIds(ids));
    }
}
