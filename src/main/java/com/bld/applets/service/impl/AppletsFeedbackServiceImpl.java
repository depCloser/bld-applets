package com.bld.applets.service.impl;

import java.util.List;

import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsFeedbackMapper;
import com.bld.applets.domain.AppletsFeedback;
import com.bld.applets.service.IAppletsFeedbackService;

/**
 * 反馈Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsFeedbackServiceImpl implements IAppletsFeedbackService 
{
    @Autowired
    private AppletsFeedbackMapper appletsFeedbackMapper;

    /**
     * 查询反馈
     * 
     * @param id 反馈ID
     * @return 反馈
     */
    @Override
    public AppletsFeedback selectAppletsFeedbackById(Long id)
    {
        return appletsFeedbackMapper.selectAppletsFeedbackById(id);
    }

    /**
     * 查询反馈列表
     * 
     * @param appletsFeedback 反馈
     * @return 反馈
     */
    @Override
    public List<AppletsFeedback> selectAppletsFeedbackList(AppletsFeedback appletsFeedback)
    {
        return appletsFeedbackMapper.selectAppletsFeedbackList(appletsFeedback);
    }

    /**
     * 新增反馈
     * 
     * @param appletsFeedback 反馈
     * @return 结果
     */
    @Override
    public int insertAppletsFeedback(AppletsFeedback appletsFeedback)
    {
        return appletsFeedbackMapper.insertAppletsFeedback(appletsFeedback);
    }

    /**
     * 修改反馈
     * 
     * @param appletsFeedback 反馈
     * @return 结果
     */
    @Override
    public int updateAppletsFeedback(AppletsFeedback appletsFeedback)
    {
        return appletsFeedbackMapper.updateAppletsFeedback(appletsFeedback);
    }

    /**
     * 删除反馈对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsFeedbackByIds(String ids)
    {
        return appletsFeedbackMapper.deleteAppletsFeedbackByIds(CommonUtils.toStrArray(ids));
    }

    /**
     * 删除反馈信息
     * 
     * @param id 反馈ID
     * @return 结果
     */
    @Override
    public int deleteAppletsFeedbackById(Long id)
    {
        return appletsFeedbackMapper.deleteAppletsFeedbackById(id);
    }
}
