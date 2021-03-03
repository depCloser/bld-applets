package com.bld.applets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsElectrovalenceMapper;
import com.bld.applets.domain.AppletsElectrovalence;
import com.bld.applets.service.IAppletsElectrovalenceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 充电桩电价Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsElectrovalenceServiceImpl implements IAppletsElectrovalenceService 
{
    @Autowired
    private AppletsElectrovalenceMapper appletsElectrovalenceMapper;

    /**
     * 查询充电桩电价
     * 
     * @param id 充电桩电价ID
     * @return 充电桩电价
     */
    @Override
    public AppletsElectrovalence selectAppletsElectrovalenceById(Long id)
    {
        return appletsElectrovalenceMapper.selectAppletsElectrovalenceById(id);
    }

    /**
     * 查询充电桩电价列表
     * 
     * @param appletsElectrovalence 充电桩电价
     * @return 充电桩电价
     */
    @Override
    public List<AppletsElectrovalence> selectAppletsElectrovalenceList(AppletsElectrovalence appletsElectrovalence)
    {
        return appletsElectrovalenceMapper.selectAppletsElectrovalenceList(appletsElectrovalence);
    }

    /**
     * 新增充电桩电价
     * 
     * @param appletsElectrovalence 充电桩电价
     * @return 结果
     */
    @Override
    public int insertAppletsElectrovalence(AppletsElectrovalence appletsElectrovalence)
    {
        return appletsElectrovalenceMapper.insertAppletsElectrovalence(appletsElectrovalence);
    }

    /**
     * 修改充电桩电价
     * 
     * @param appletsElectrovalence 充电桩电价
     * @return 结果
     */
    @Override
    public int updateAppletsElectrovalence(AppletsElectrovalence appletsElectrovalence)
    {
        return appletsElectrovalenceMapper.updateAppletsElectrovalence(appletsElectrovalence);
    }

    /**
     * 删除充电桩电价对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsElectrovalenceByIds(String ids)
    {
        return appletsElectrovalenceMapper.deleteAppletsElectrovalenceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除充电桩电价信息
     * 
     * @param id 充电桩电价ID
     * @return 结果
     */
    @Override
    public int deleteAppletsElectrovalenceById(Long id)
    {
        return appletsElectrovalenceMapper.deleteAppletsElectrovalenceById(id);
    }
}
