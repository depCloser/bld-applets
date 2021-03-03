package com.bld.applets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bld.applets.mapper.AppletsMemberMapper;
import com.bld.applets.domain.AppletsMember;
import com.bld.applets.service.IAppletsMemberService;
import com.ruoyi.common.core.text.Convert;

/**
 * 会员等级配置Service业务层处理
 * 
 * @author tyx
 * @date 2021-03-03
 */
@Service
public class AppletsMemberServiceImpl implements IAppletsMemberService 
{
    @Autowired
    private AppletsMemberMapper appletsMemberMapper;

    /**
     * 查询会员等级配置
     * 
     * @param id 会员等级配置ID
     * @return 会员等级配置
     */
    @Override
    public AppletsMember selectAppletsMemberById(Long id)
    {
        return appletsMemberMapper.selectAppletsMemberById(id);
    }

    /**
     * 查询会员等级配置列表
     * 
     * @param appletsMember 会员等级配置
     * @return 会员等级配置
     */
    @Override
    public List<AppletsMember> selectAppletsMemberList(AppletsMember appletsMember)
    {
        return appletsMemberMapper.selectAppletsMemberList(appletsMember);
    }

    /**
     * 新增会员等级配置
     * 
     * @param appletsMember 会员等级配置
     * @return 结果
     */
    @Override
    public int insertAppletsMember(AppletsMember appletsMember)
    {
        return appletsMemberMapper.insertAppletsMember(appletsMember);
    }

    /**
     * 修改会员等级配置
     * 
     * @param appletsMember 会员等级配置
     * @return 结果
     */
    @Override
    public int updateAppletsMember(AppletsMember appletsMember)
    {
        return appletsMemberMapper.updateAppletsMember(appletsMember);
    }

    /**
     * 删除会员等级配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppletsMemberByIds(String ids)
    {
        return appletsMemberMapper.deleteAppletsMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员等级配置信息
     * 
     * @param id 会员等级配置ID
     * @return 结果
     */
    @Override
    public int deleteAppletsMemberById(Long id)
    {
        return appletsMemberMapper.deleteAppletsMemberById(id);
    }
}
