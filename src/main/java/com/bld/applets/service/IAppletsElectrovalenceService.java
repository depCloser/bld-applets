package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsElectrovalence;

/**
 * 充电桩电价Service接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface IAppletsElectrovalenceService 
{
    /**
     * 查询充电桩电价
     * 
     * @param id 充电桩电价ID
     * @return 充电桩电价
     */
    public AppletsElectrovalence selectAppletsElectrovalenceById(Long id);

    /**
     * 查询充电桩电价列表
     * 
     * @param appletsElectrovalence 充电桩电价
     * @return 充电桩电价集合
     */
    public List<AppletsElectrovalence> selectAppletsElectrovalenceList(AppletsElectrovalence appletsElectrovalence);

    /**
     * 新增充电桩电价
     * 
     * @param appletsElectrovalence 充电桩电价
     * @return 结果
     */
    public int insertAppletsElectrovalence(AppletsElectrovalence appletsElectrovalence);

    public int batchInsertAppletsElectrovalence(List<AppletsElectrovalence> list);

    /**
     * 修改充电桩电价
     * 
     * @param appletsElectrovalence 充电桩电价
     * @return 结果
     */
    public int updateAppletsElectrovalence(AppletsElectrovalence appletsElectrovalence);

    /**
     * 批量删除充电桩电价
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsElectrovalenceByIds(String ids);

    /**
     * 删除充电桩电价信息
     * 
     * @param id 充电桩电价ID
     * @return 结果
     */
    public int deleteAppletsElectrovalenceById(Long id);

    /**
     * @Author: tyx
     * @Description: 删除充电桩相关联的所有电价记录
     * @Param: [id]
     * @return: int
     * @Date: 2021/4/6
     */
    public int deleteAllElectrovalenceByPilesId(Long pid);

}
