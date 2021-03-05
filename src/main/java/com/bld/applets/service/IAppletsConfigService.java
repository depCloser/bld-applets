package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsConfig;

/**
 * 小程序参数配置Service接口
 * 
 * @author tyx
 * @date 2021-03-04
 */
public interface IAppletsConfigService 
{
    /**
     * 查询小程序参数配置
     * 
     * @param id 小程序参数配置ID
     * @return 小程序参数配置
     */
    public AppletsConfig selectAppletsConfigById(Long id);

    /**
     * 查询小程序参数配置列表
     * 
     * @param appletsConfig 小程序参数配置
     * @return 小程序参数配置集合
     */
    public List<AppletsConfig> selectAppletsConfigList(AppletsConfig appletsConfig);

    /**
     * 新增小程序参数配置
     * 
     * @param appletsConfig 小程序参数配置
     * @return 结果
     */
    public int insertAppletsConfig(AppletsConfig appletsConfig);

    /**
     * 修改小程序参数配置
     * 
     * @param appletsConfig 小程序参数配置
     * @return 结果
     */
    public int updateAppletsConfig(AppletsConfig appletsConfig);

    /**
     * 批量删除小程序参数配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsConfigByIds(String ids);

    /**
     * 删除小程序参数配置信息
     * 
     * @param id 小程序参数配置ID
     * @return 结果
     */
    public int deleteAppletsConfigById(Long id);
}
