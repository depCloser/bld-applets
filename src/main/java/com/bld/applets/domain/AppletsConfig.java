package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序参数配置对象 applets_config
 * 
 * @author tyx
 * @date 2021-03-04
 */
public class AppletsConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 参数名 */
    @Excel(name = "参数名")
    private String name;

    /** 键 */
    @Excel(name = "键")
    private String key;

    /** 值 */
    @Excel(name = "值")
    private String value;

    /** 创建者 */
    @Excel(name = "创建者")
    private String owner;

    public AppletsConfig setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsConfig setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getName() 
    {
        return name;
    }
    public AppletsConfig setKey(String key)
    {
        this.key = key;
        return this;
    }

    public String getKey() 
    {
        return key;
    }
    public AppletsConfig setValue(String value)
    {
        this.value = value;
        return this;
    }

    public String getValue() 
    {
        return value;
    }
    public AppletsConfig setOwner(String owner)
    {
        this.owner = owner;
        return this;
    }

    public String getOwner() 
    {
        return owner;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("key", getKey())
            .append("value", getValue())
            .append("owner", getOwner())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
