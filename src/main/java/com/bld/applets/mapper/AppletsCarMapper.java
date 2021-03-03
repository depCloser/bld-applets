package com.bld.applets.mapper;

import java.util.List;
import com.bld.applets.domain.AppletsCar;

/**
 * 用户车辆Mapper接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface AppletsCarMapper 
{
    /**
     * 查询用户车辆
     * 
     * @param id 用户车辆ID
     * @return 用户车辆
     */
    public AppletsCar selectAppletsCarById(Long id);

    /**
     * 查询用户车辆列表
     * 
     * @param appletsCar 用户车辆
     * @return 用户车辆集合
     */
    public List<AppletsCar> selectAppletsCarList(AppletsCar appletsCar);

    /**
     * 新增用户车辆
     * 
     * @param appletsCar 用户车辆
     * @return 结果
     */
    public int insertAppletsCar(AppletsCar appletsCar);

    /**
     * 修改用户车辆
     * 
     * @param appletsCar 用户车辆
     * @return 结果
     */
    public int updateAppletsCar(AppletsCar appletsCar);

    /**
     * 删除用户车辆
     * 
     * @param id 用户车辆ID
     * @return 结果
     */
    public int deleteAppletsCarById(Long id);

    /**
     * 批量删除用户车辆
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsCarByIds(String[] ids);
}
