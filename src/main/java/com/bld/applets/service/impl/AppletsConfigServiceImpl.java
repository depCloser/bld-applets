package com.bld.applets.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bld.applets.utils.CacheUtils;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsConfigMapper;
import com.bld.applets.domain.AppletsConfig;
import com.bld.applets.service.IAppletsConfigService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.PostConstruct;

/**
 * 小程序参数配置Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-04
 */
@Service
public class AppletsConfigServiceImpl implements IAppletsConfigService 
{
    @Autowired
    private AppletsConfigMapper appletsConfigMapper;

    @PostConstruct
    public void init () {
        Map<String, String> collect = appletsConfigMapper.selectAppletsConfigList(new AppletsConfig())
                .stream()
                .collect(Collectors.toMap(AppletsConfig::getKey, AppletsConfig::getValue));
        CacheUtils.putAll(collect);
    }

    /**
     * 查询小程序参数配置
     * 
     * @param id 小程序参数配置ID
     * @return 小程序参数配置
     */
    @Override
    public AppletsConfig selectAppletsConfigById(Long id)
    {
        return appletsConfigMapper.selectAppletsConfigById(id);
    }

    /**
     * 查询小程序参数配置列表
     * 
     * @param appletsConfig 小程序参数配置
     * @return 小程序参数配置
     */
    @Override
    public List<AppletsConfig> selectAppletsConfigList(AppletsConfig appletsConfig)
    {
        return appletsConfigMapper.selectAppletsConfigList(appletsConfig);
    }

    /**
     * 新增小程序参数配置
     * 
     * @param appletsConfig 小程序参数配置
     * @return 结果
     */
    @Override
    public int insertAppletsConfig(AppletsConfig appletsConfig)
    {
        appletsConfig.setCreateTime(DateUtils.getNowDate());
        return appletsConfigMapper.insertAppletsConfig(appletsConfig);
    }

    /**
     * 修改小程序参数配置
     * 
     * @param appletsConfig 小程序参数配置
     * @return 结果
     */
    @Override
    public int updateAppletsConfig(AppletsConfig appletsConfig)
    {
        appletsConfig.setUpdateTime(DateUtils.getNowDate());
        return appletsConfigMapper.updateAppletsConfig(appletsConfig);
    }

    /**
     * 删除小程序参数配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsConfigByIds(String ids)
    {
        return appletsConfigMapper.deleteAppletsConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除小程序参数配置信息
     * 
     * @param id 小程序参数配置ID
     * @return 结果
     */
    @Override
    public int deleteAppletsConfigById(Long id)
    {
        return appletsConfigMapper.deleteAppletsConfigById(id);
    }
}
