package com.bld.applets.mapper;

import java.util.List;
import com.bld.applets.domain.AppletsUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户Mapper接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface AppletsUserMapper 
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
     * 删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteAppletsUserById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsUserByIds(String[] ids);

    /*
     * @Author: tyx
     * @Description: 查询月度充电量  
     * @Param: []
     * @return: java.lang.Long
     * @Date: 2021/3/4
     */
    @Select("SELECT IFNULL(SUM(charge),0) FROM applets_order where status != 0 and createTime = CURDATE() and user_id = #{userId}")
    public Long getThisMonthCharge (Long userId);

}
