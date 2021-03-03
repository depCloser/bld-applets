package com.bld.applets.mapper;

import java.util.List;
import com.bld.applets.domain.AppletsMember;

/**
 * 会员等级配置Mapper接口
 * 
 * @author tyx
 * @date 2021-03-03
 */
public interface AppletsMemberMapper 
{
    /**
     * 查询会员等级配置
     * 
     * @param id 会员等级配置ID
     * @return 会员等级配置
     */
    public AppletsMember selectAppletsMemberById(Long id);

    /**
     * 查询会员等级配置列表
     * 
     * @param appletsMember 会员等级配置
     * @return 会员等级配置集合
     */
    public List<AppletsMember> selectAppletsMemberList(AppletsMember appletsMember);

    /**
     * 新增会员等级配置
     * 
     * @param appletsMember 会员等级配置
     * @return 结果
     */
    public int insertAppletsMember(AppletsMember appletsMember);

    /**
     * 修改会员等级配置
     * 
     * @param appletsMember 会员等级配置
     * @return 结果
     */
    public int updateAppletsMember(AppletsMember appletsMember);

    /**
     * 删除会员等级配置
     * 
     * @param id 会员等级配置ID
     * @return 结果
     */
    public int deleteAppletsMemberById(Long id);

    /**
     * 批量删除会员等级配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppletsMemberByIds(String[] ids);
}
