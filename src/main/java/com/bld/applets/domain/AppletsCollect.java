package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户收藏（用户-充电桩关联，多对多）对象 applets_collect
 * 
 * @author tyx
 * @date 2021-03-03
 */
public class AppletsCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 充电桩id */
    @Excel(name = "充电桩id")
    private Long pilesId;

    public AppletsCollect setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsCollect setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public AppletsCollect setPilesId(Long pilesId)
    {
        this.pilesId = pilesId;
        return this;
    }

    public Long getPilesId() 
    {
        return pilesId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("pilesId", getPilesId())
            .toString();
    }
}