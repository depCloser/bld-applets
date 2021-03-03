package com.bld.applets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsCollectMapper;
import com.bld.applets.domain.AppletsCollect;
import com.bld.applets.service.IAppletsCollectService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户收藏（用户-充电桩关联，多对多）Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsCollectServiceImpl implements IAppletsCollectService 
{
    @Autowired
    private AppletsCollectMapper appletsCollectMapper;

    /**
     * 查询用户收藏（用户-充电桩关联，多对多）
     * 
     * @param id 用户收藏（用户-充电桩关联，多对多）ID
     * @return 用户收藏（用户-充电桩关联，多对多）
     */
    @Override
    public AppletsCollect selectAppletsCollectById(Long id)
    {
        return appletsCollectMapper.selectAppletsCollectById(id);
    }

    /**
     * 查询用户收藏（用户-充电桩关联，多对多）列表
     * 
     * @param appletsCollect 用户收藏（用户-充电桩关联，多对多）
     * @return 用户收藏（用户-充电桩关联，多对多）
     */
    @Override
    public List<AppletsCollect> selectAppletsCollectList(AppletsCollect appletsCollect)
    {
        return appletsCollectMapper.selectAppletsCollectList(appletsCollect);
    }

    /**
     * 新增用户收藏（用户-充电桩关联，多对多）
     * 
     * @param appletsCollect 用户收藏（用户-充电桩关联，多对多）
     * @return 结果
     */
    @Override
    public int insertAppletsCollect(AppletsCollect appletsCollect)
    {
        return appletsCollectMapper.insertAppletsCollect(appletsCollect);
    }

    /**
     * 修改用户收藏（用户-充电桩关联，多对多）
     * 
     * @param appletsCollect 用户收藏（用户-充电桩关联，多对多）
     * @return 结果
     */
    @Override
    public int updateAppletsCollect(AppletsCollect appletsCollect)
    {
        return appletsCollectMapper.updateAppletsCollect(appletsCollect);
    }

    /**
     * 删除用户收藏（用户-充电桩关联，多对多）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsCollectByIds(String ids)
    {
        return appletsCollectMapper.deleteAppletsCollectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户收藏（用户-充电桩关联，多对多）信息
     * 
     * @param id 用户收藏（用户-充电桩关联，多对多）ID
     * @return 结果
     */
    @Override
    public int deleteAppletsCollectById(Long id)
    {
        return appletsCollectMapper.deleteAppletsCollectById(id);
    }
}
