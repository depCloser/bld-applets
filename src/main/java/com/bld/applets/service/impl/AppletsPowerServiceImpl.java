package com.bld.applets.service.impl;

import java.util.List;

import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsPowerMapper;
import com.bld.applets.domain.AppletsPower;
import com.bld.applets.service.IAppletsPowerService;

/**
 * 功率图Service业务层处理
 * 
 * @author tyx
 * @date 2021-04-12
 */
@Service
public class AppletsPowerServiceImpl implements IAppletsPowerService 
{
    @Autowired
    private AppletsPowerMapper appletsPowerMapper;

    /**
     * 查询功率图
     * 
     * @param id 功率图ID
     * @return 功率图
     */
    @Override
    public AppletsPower selectAppletsPowerById(Long id)
    {
        return appletsPowerMapper.selectAppletsPowerById(id);
    }

    @Override
    public AppletsPower getLastRecord(Long pId) {
        return appletsPowerMapper.getLastRecord(pId);
    }

    /**
     * 查询功率图列表
     * 
     * @param appletsPower 功率图
     * @return 功率图
     */
    @Override
    public List<AppletsPower> selectAppletsPowerList(AppletsPower appletsPower)
    {
        return appletsPowerMapper.selectAppletsPowerList(appletsPower);
    }

    @Override
    public List<AppletsPower> selectPowerBySql(String sql) {
        return appletsPowerMapper.selectPowerBySql(sql);
    }

    @Override
    public List<AppletsPower> selectTodayPowerList(AppletsPower appletsPower) {
        return null;
    }

    /**
     * 新增功率图
     * 
     * @param appletsPower 功率图
     * @return 结果
     */
    @Override
    public int insertAppletsPower(AppletsPower appletsPower)
    {
        return appletsPowerMapper.insertAppletsPower(appletsPower);
    }

    /**
     * 修改功率图
     * 
     * @param appletsPower 功率图
     * @return 结果
     */
    @Override
    public int updateAppletsPower(AppletsPower appletsPower)
    {
        return appletsPowerMapper.updateAppletsPower(appletsPower);
    }

    /**
     * 删除功率图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsPowerByIds(String ids)
    {
        return appletsPowerMapper.deleteAppletsPowerByIds(CommonUtils.toStrArray(ids));
    }

    /**
     * 删除功率图信息
     * 
     * @param id 功率图ID
     * @return 结果
     */
    @Override
    public int deleteAppletsPowerById(Long id)
    {
        return appletsPowerMapper.deleteAppletsPowerById(id);
    }
}
