package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员等级配置对象 applets_member
 * 
 * @author tyx
 * @date 2021-03-03
 */
public class AppletsMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会员等级 */
    @Excel(name = "会员等级")
    private Integer level;

    /** 目标充电量 */
    @Excel(name = "目标充电量")
    private Long charge;

    public AppletsMember setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsMember setLevel(Integer level)
    {
        this.level = level;
        return this;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public AppletsMember setCharge(Long charge)
    {
        this.charge = charge;
        return this;
    }

    public Long getCharge() 
    {
        return charge;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("level", getLevel())
            .append("charge", getCharge())
            .toString();
    }
}
