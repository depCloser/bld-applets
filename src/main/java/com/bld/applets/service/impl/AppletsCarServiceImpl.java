package com.bld.applets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsCarMapper;
import com.bld.applets.domain.AppletsCar;
import com.bld.applets.service.IAppletsCarService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户车辆Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsCarServiceImpl implements IAppletsCarService 
{
    @Autowired
    private AppletsCarMapper appletsCarMapper;

    /**
     * 查询用户车辆
     * 
     * @param id 用户车辆ID
     * @return 用户车辆
     */
    @Override
    public AppletsCar selectAppletsCarById(Long id)
    {
        return appletsCarMapper.selectAppletsCarById(id);
    }

    /**
     * 查询用户车辆列表
     * 
     * @param appletsCar 用户车辆
     * @return 用户车辆
     */
    @Override
    public List<AppletsCar> selectAppletsCarList(AppletsCar appletsCar)
    {
        return appletsCarMapper.selectAppletsCarList(appletsCar);
    }

    /**
     * 新增用户车辆
     * 
     * @param appletsCar 用户车辆
     * @return 结果
     */
    @Override
    public int insertAppletsCar(AppletsCar appletsCar)
    {
        return appletsCarMapper.insertAppletsCar(appletsCar);
    }

    /**
     * 修改用户车辆
     * 
     * @param appletsCar 用户车辆
     * @return 结果
     */
    @Override
    public int updateAppletsCar(AppletsCar appletsCar)
    {
        return appletsCarMapper.updateAppletsCar(appletsCar);
    }

    /**
     * 删除用户车辆对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsCarByIds(String ids)
    {
        return appletsCarMapper.deleteAppletsCarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户车辆信息
     * 
     * @param id 用户车辆ID
     * @return 结果
     */
    @Override
    public int deleteAppletsCarById(Long id)
    {
        return appletsCarMapper.deleteAppletsCarById(id);
    }
}
