package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsComment;

/**
 * 评论Service接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface IAppletsCommentService 
{
    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    public AppletsComment selectAppletsCommentById(Long id);

    /**
     * 查询评论列表
     * 
     * @param appletsComment 评论
     * @return 评论集合
     */
    public List<AppletsComment> selectAppletsCommentList(AppletsComment appletsComment);

    /**
     * 新增评论
     * 
     * @param appletsComment 评论
     * @return 结果
     */
    public int insertAppletsComment(AppletsComment appletsComment);

    /**
     * 修改评论
     * 
     * @param appletsComment 评论
     * @return 结果
     */
    public int updateAppletsComment(AppletsComment appletsComment);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsCommentByIds(String ids);

    /**
     * 删除评论信息
     * 
     * @param id 评论ID
     * @return 结果
     */
    public int deleteAppletsCommentById(Long id);
}
