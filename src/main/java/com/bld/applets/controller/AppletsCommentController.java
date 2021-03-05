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
import com.bld.applets.domain.AppletsComment;
import com.bld.applets.service.IAppletsCommentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论Controller
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/comment")
public class AppletsCommentController extends BaseController
{

    @Autowired
    private IAppletsCommentService appletsCommentService;

    /**
     * 查询评论列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsComment appletsComment)
    {
        startPage();
        List<AppletsComment> list = appletsCommentService.selectAppletsCommentList(appletsComment.setUserId(CommonUtils.getUserId()));
        return getDataTable(list);
    }

    /**
     * 新增保存评论
     */
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsComment appletsComment)
    {
        return toAjax(appletsCommentService.insertAppletsComment(appletsComment.setUserId(CommonUtils.getUserId())));
    }

    /**
     * 修改保存评论
     */
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsComment appletsComment)
    {
        return toAjax(appletsCommentService.updateAppletsComment(appletsComment));
    }

    /**
     * 删除评论
     */
    @Log(title = "评论", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsCommentService.deleteAppletsCommentByIds(ids));
    }
}
