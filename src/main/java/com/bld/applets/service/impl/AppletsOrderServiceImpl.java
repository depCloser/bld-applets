package com.bld.applets.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsOrderMapper;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.service.IAppletsOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 订单Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsOrderServiceImpl implements IAppletsOrderService 
{
    @Autowired
    private AppletsOrderMapper appletsOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public AppletsOrder selectAppletsOrderById(Long id)
    {
        return appletsOrderMapper.selectAppletsOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param appletsOrder 订单
     * @return 订单
     */
    @Override
    public List<AppletsOrder> selectAppletsOrderList(AppletsOrder appletsOrder)
    {
        return appletsOrderMapper.selectAppletsOrderList(appletsOrder);
    }

    /**
     * 新增订单
     * 
     * @param appletsOrder 订单
     * @return 结果
     */
    @Override
    public int insertAppletsOrder(AppletsOrder appletsOrder)
    {
        return appletsOrderMapper.insertAppletsOrder(appletsOrder);
    }

    /**
     * 修改订单
     * 
     * @param appletsOrder 订单
     * @return 结果
     */
    @Override
    public int updateAppletsOrder(AppletsOrder appletsOrder)
    {
        appletsOrder.setUpdateTime(DateUtils.getNowDate());
        return appletsOrderMapper.updateAppletsOrder(appletsOrder);
    }

    /**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsOrderByIds(String ids)
    {
        return appletsOrderMapper.deleteAppletsOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteAppletsOrderById(Long id)
    {
        return appletsOrderMapper.deleteAppletsOrderById(id);
    }
}
