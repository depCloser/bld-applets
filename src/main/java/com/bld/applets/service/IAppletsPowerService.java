package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsPower;

/**
 * 功率图Service接口
 * 
 * @author tyx
 * @date 2021-04-12
 */
public interface IAppletsPowerService 
{
    /**
     * 查询功率图
     * 
     * @param id 功率图ID
     * @return 功率图
     */
    public AppletsPower selectAppletsPowerById(Long id);

    public AppletsPower getLastRecord(Long pId);

    /**
     * 查询功率图列表
     * 
     * @param appletsPower 功率图
     * @return 功率图集合
     */
    public List<AppletsPower> selectAppletsPowerList(AppletsPower appletsPower);

    public List<AppletsPower> selectTodayPowerList(AppletsPower appletsPower);

    /**
     * 新增功率图
     * 
     * @param appletsPower 功率图
     * @return 结果
     */
    public int insertAppletsPower(AppletsPower appletsPower);

    /**
     * 修改功率图
     * 
     * @param appletsPower 功率图
     * @return 结果
     */
    public int updateAppletsPower(AppletsPower appletsPower);

    /**
     * 批量删除功率图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsPowerByIds(String ids);

    /**
     * 删除功率图信息
     * 
     * @param id 功率图ID
     * @return 结果
     */
    public int deleteAppletsPowerById(Long id);
}
