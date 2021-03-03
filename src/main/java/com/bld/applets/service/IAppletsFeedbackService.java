package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsFeedback;

/**
 * 反馈Service接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface IAppletsFeedbackService 
{
    /**
     * 查询反馈
     * 
     * @param id 反馈ID
     * @return 反馈
     */
    public AppletsFeedback selectAppletsFeedbackById(Long id);

    /**
     * 查询反馈列表
     * 
     * @param appletsFeedback 反馈
     * @return 反馈集合
     */
    public List<AppletsFeedback> selectAppletsFeedbackList(AppletsFeedback appletsFeedback);

    /**
     * 新增反馈
     * 
     * @param appletsFeedback 反馈
     * @return 结果
     */
    public int insertAppletsFeedback(AppletsFeedback appletsFeedback);

    /**
     * 修改反馈
     * 
     * @param appletsFeedback 反馈
     * @return 结果
     */
    public int updateAppletsFeedback(AppletsFeedback appletsFeedback);

    /**
     * 批量删除反馈
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsFeedbackByIds(String ids);

    /**
     * 删除反馈信息
     * 
     * @param id 反馈ID
     * @return 结果
     */
    public int deleteAppletsFeedbackById(Long id);
}
