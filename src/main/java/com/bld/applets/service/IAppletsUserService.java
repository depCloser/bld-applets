package com.bld.applets.service;

import java.util.List;
import com.bld.applets.domain.AppletsUser;

/**
 * 用户Service接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface IAppletsUserService 
{
    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    public AppletsUser selectAppletsUserById(Long id);

    /**
     * 查询用户列表
     * 
     * @param appletsUser 用户
     * @return 用户集合
     */
    public List<AppletsUser> selectAppletsUserList(AppletsUser appletsUser);

    /**
     * 新增用户
     * 
     * @param appletsUser 用户
     * @return 结果
     */
    public int insertAppletsUser(AppletsUser appletsUser);

    /**
     * 修改用户
     * 
     * @param appletsUser 用户
     * @return 结果
     */
    public int updateAppletsUser(AppletsUser appletsUser);

    public int updateUserBySql(String sql);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsUserByIds(String ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteAppletsUserById(Long id);

    public Long getThisMonthCharge (Long userId);

}
