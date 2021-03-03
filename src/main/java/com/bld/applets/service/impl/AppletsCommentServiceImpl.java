package com.bld.applets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsCommentMapper;
import com.bld.applets.domain.AppletsComment;
import com.bld.applets.service.IAppletsCommentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 评论Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsCommentServiceImpl implements IAppletsCommentService 
{
    @Autowired
    private AppletsCommentMapper appletsCommentMapper;

    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    @Override
    public AppletsComment selectAppletsCommentById(Long id)
    {
        return appletsCommentMapper.selectAppletsCommentById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param appletsComment 评论
     * @return 评论
     */
    @Override
    public List<AppletsComment> selectAppletsCommentList(AppletsComment appletsComment)
    {
        return appletsCommentMapper.selectAppletsCommentList(appletsComment);
    }

    /**
     * 新增评论
     * 
     * @param appletsComment 评论
     * @return 结果
     */
    @Override
    public int insertAppletsComment(AppletsComment appletsComment)
    {
        return appletsCommentMapper.insertAppletsComment(appletsComment);
    }

    /**
     * 修改评论
     * 
     * @param appletsComment 评论
     * @return 结果
     */
    @Override
    public int updateAppletsComment(AppletsComment appletsComment)
    {
        return appletsCommentMapper.updateAppletsComment(appletsComment);
    }

    /**
     * 删除评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsCommentByIds(String ids)
    {
        return appletsCommentMapper.deleteAppletsCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论ID
     * @return 结果
     */
    @Override
    public int deleteAppletsCommentById(Long id)
    {
        return appletsCommentMapper.deleteAppletsCommentById(id);
    }
}
