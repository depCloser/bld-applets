package com.bld.applets.controller;

import java.util.Date;
import java.util.List;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsComment;
import com.bld.applets.service.IAppletsCommentService;

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
    @GetMapping("/list")
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
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsComment appletsComment)
    {
        appletsComment.setCreateTime(new Date()).setUpdateTime(new Date());
        return toAjax(appletsCommentService.insertAppletsComment(appletsComment.setUserId(CommonUtils.getUserId())));
    }

    /**
     * 修改保存评论
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsComment appletsComment)
    {
        appletsComment.setUpdateTime(new Date());
        return toAjax(appletsCommentService.updateAppletsComment(appletsComment));
    }

    /**
     * 删除评论
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsCommentService.deleteAppletsCommentByIds(ids));
    }
}
