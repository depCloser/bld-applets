package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsPiles;

/**
 * 充电桩Service接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface IAppletsPilesService 
{
    /**
     * 查询充电桩
     * 
     * @param id 充电桩ID
     * @return 充电桩
     */
    public AppletsPiles selectAppletsPilesById(Long id);

    /**
     * 查询充电桩列表
     * 
     * @param appletsPiles 充电桩
     * @return 充电桩集合
     */
    public List<AppletsPiles> selectAppletsPilesList(AppletsPiles appletsPiles);

    /**
     * 新增充电桩
     * 
     * @param appletsPiles 充电桩
     * @return 结果
     */
    public int insertAppletsPiles(AppletsPiles appletsPiles);

    /**
     * 修改充电桩
     * 
     * @param appletsPiles 充电桩
     * @return 结果
     */
    public int updateAppletsPiles(AppletsPiles appletsPiles);

    /**
     * 批量删除充电桩
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsPilesByIds(String ids);

    /**
     * 删除充电桩信息
     * 
     * @param id 充电桩ID
     * @return 结果
     */
    public int deleteAppletsPilesById(Long id);
}
