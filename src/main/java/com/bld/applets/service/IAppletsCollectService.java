package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsCollect;

/**
 * 用户收藏（用户-充电桩关联，多对多）Service接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface IAppletsCollectService 
{
    /**
     * 查询用户收藏（用户-充电桩关联，多对多）
     * 
     * @param id 用户收藏（用户-充电桩关联，多对多）ID
     * @return 用户收藏（用户-充电桩关联，多对多）
     */
    public AppletsCollect selectAppletsCollectById(Long id);

    /**
     * 查询用户收藏（用户-充电桩关联，多对多）列表
     * 
     * @param appletsCollect 用户收藏（用户-充电桩关联，多对多）
     * @return 用户收藏（用户-充电桩关联，多对多）集合
     */
    public List<AppletsCollect> selectAppletsCollectList(AppletsCollect appletsCollect);

    /**
     * 新增用户收藏（用户-充电桩关联，多对多）
     * 
     * @param appletsCollect 用户收藏（用户-充电桩关联，多对多）
     * @return 结果
     */
    public int insertAppletsCollect(AppletsCollect appletsCollect);

    /**
     * 修改用户收藏（用户-充电桩关联，多对多）
     * 
     * @param appletsCollect 用户收藏（用户-充电桩关联，多对多）
     * @return 结果
     */
    public int updateAppletsCollect(AppletsCollect appletsCollect);

    /**
     * 批量删除用户收藏（用户-充电桩关联，多对多）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsCollectByIds(String ids);

    /**
     * 删除用户收藏（用户-充电桩关联，多对多）信息
     * 
     * @param id 用户收藏（用户-充电桩关联，多对多）ID
     * @return 结果
     */
    public int deleteAppletsCollectById(Long id);
}
