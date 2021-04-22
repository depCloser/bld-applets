package com.bld.applets.mapper;

import java.util.List;
import com.bld.applets.domain.AppletsOrder;
import org.apache.ibatis.annotations.Select;

/**
 * 订单Mapper接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface AppletsOrderMapper 
{

    @Select("select * from applets_order where payment_type = 2 and status != 1 and user_id = #{userId}")
    public List<AppletsOrder> selectCompletOrder(Long userId);

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public AppletsOrder selectAppletsOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param appletsOrder 订单
     * @return 订单集合
     */
    public List<AppletsOrder> selectAppletsOrderList(AppletsOrder appletsOrder);

    /**
     * 新增订单
     * 
     * @param appletsOrder 订单
     * @return 结果
     */
    public int insertAppletsOrder(AppletsOrder appletsOrder);

    /**
     * 修改订单
     * 
     * @param appletsOrder 订单
     * @return 结果
     */
    public int updateAppletsOrder(AppletsOrder appletsOrder);

    /**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteAppletsOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsOrderByIds(String[] ids);

    public List<AppletsOrder> queryProfit(Long userId);

}
