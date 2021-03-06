package com.bld.applets.service.impl;

import java.util.List;

import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsPilesMapper;
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.service.IAppletsPilesService;

/**
 * 充电桩Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsPilesServiceImpl implements IAppletsPilesService 
{
    @Autowired
    private AppletsPilesMapper appletsPilesMapper;

    /**
     * 查询充电桩
     * 
     * @param id 充电桩ID
     * @return 充电桩
     */
    @Override
    public AppletsPiles selectAppletsPilesById(Long id)
    {
        return appletsPilesMapper.selectAppletsPilesById(id);
    }

    /**
     * 查询充电桩列表
     * 
     * @param appletsPiles 充电桩
     * @return 充电桩
     */
    @Override
    public List<AppletsPiles> selectAppletsPilesList(AppletsPiles appletsPiles)
    {
        return appletsPilesMapper.selectAppletsPilesList(appletsPiles);
    }

    /**
     * 新增充电桩
     * 
     * @param appletsPiles 充电桩
     * @return 结果
     */
    @Override
    public int insertAppletsPiles(AppletsPiles appletsPiles)
    {
        return appletsPilesMapper.insertAppletsPiles(appletsPiles);
    }

    /**
     * 修改充电桩
     * 
     * @param appletsPiles 充电桩
     * @return 结果
     */
    @Override
    public int updateAppletsPiles(AppletsPiles appletsPiles)
    {
        return appletsPilesMapper.updateAppletsPiles(appletsPiles);
    }

    /**
     * 删除充电桩对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsPilesByIds(String ids)
    {
        return appletsPilesMapper.deleteAppletsPilesByIds(CommonUtils.toStrArray(ids));
    }

    /**
     * 删除充电桩信息
     * 
     * @param id 充电桩ID
     * @return 结果
     */
    @Override
    public int deleteAppletsPilesById(Long id)
    {
        return appletsPilesMapper.deleteAppletsPilesById(id);
    }
}
