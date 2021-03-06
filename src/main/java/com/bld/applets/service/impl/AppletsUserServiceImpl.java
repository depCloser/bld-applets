package com.bld.applets.service.impl;

import java.util.List;

import com.bld.applets.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsUserMapper;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsUserService;

/**
 * 用户Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsUserServiceImpl implements IAppletsUserService 
{
    @Autowired
    private AppletsUserMapper appletsUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public AppletsUser selectAppletsUserById(Long id)
    {
        return appletsUserMapper.selectAppletsUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param appletsUser 用户
     * @return 用户
     */
    @Override
    public List<AppletsUser> selectAppletsUserList(AppletsUser appletsUser)
    {
        return appletsUserMapper.selectAppletsUserList(appletsUser);
    }

    /**
     * 新增用户
     * 
     * @param appletsUser 用户
     * @return 结果
     */
    @Override
    public int insertAppletsUser(AppletsUser appletsUser)
    {
        return appletsUserMapper.insertAppletsUser(appletsUser);
    }

    /**
     * 修改用户
     * 
     * @param appletsUser 用户
     * @return 结果
     */
    @Override
    public int updateAppletsUser(AppletsUser appletsUser)
    {
        return appletsUserMapper.updateAppletsUser(appletsUser);
    }

    /**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsUserByIds(String ids)
    {
        return appletsUserMapper.deleteAppletsUserByIds(CommonUtils.toStrArray(ids));
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteAppletsUserById(Long id)
    {
        return appletsUserMapper.deleteAppletsUserById(id);
    }

    @Override
    public Long getThisMonthCharge() {
        return appletsUserMapper.getThisMonthCharge();
    }

}
