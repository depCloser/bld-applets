package com.bld.applets.mapper;

import java.util.List;
import com.bld.applets.domain.AppletsPower;
import org.apache.ibatis.annotations.Select;

/**
 * 功率图Mapper接口
 * 
 * @author tyx
 * @date 2021-04-12
 */
public interface AppletsPowerMapper 
{
    /**
     * 查询功率图
     * 
     * @param id 功率图ID
     * @return 功率图
     */
    public AppletsPower selectAppletsPowerById(Long id);

    @Select("SELECT * FROM applets_power WHERE p_id = #{pId} ORDER BY createTime desc LIMIT 0,1")
    public AppletsPower getLastRecord(Long pId);

    /**
     * 查询功率图列表
     * 
     * @param appletsPower 功率图
     * @return 功率图集合
     */
    public List<AppletsPower> selectAppletsPowerList(AppletsPower appletsPower);

    public List<AppletsPower> selectPowerBySql(String sql);

    @Select("select * from applets_power where p_id = #{pId} and date = CURRENT_DATE()")
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
     * 删除功率图
     * 
     * @param id 功率图ID
     * @return 结果
     */
    public int deleteAppletsPowerById(Long id);

    /**
     * 批量删除功率图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsPowerByIds(String[] ids);
}
