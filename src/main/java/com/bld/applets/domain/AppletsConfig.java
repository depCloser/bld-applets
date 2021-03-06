package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 小程序参数配置对象 applets_config
 * 
 * @author tyx
 * @date 2021-03-06
 */
public class AppletsConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 参数名 */
    private String configName;

    /** 键 */
    private String configKey;

    /** 值 */
    private String configValue;

    /** 创建者 */
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
    public AppletsConfig setConfigName(String configName)
    {
        this.configName = configName;
        return this;
    }

    public String getConfigName() 
    {
        return configName;
    }
    public AppletsConfig setConfigKey(String configKey)
    {
        this.configKey = configKey;
        return this;
    }

    public String getConfigKey() 
    {
        return configKey;
    }
    public AppletsConfig setConfigValue(String configValue)
    {
        this.configValue = configValue;
        return this;
    }

    public String getConfigValue() 
    {
        return configValue;
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
            .append("configName", getConfigName())
            .append("configKey", getConfigKey())
            .append("configValue", getConfigValue())
            .append("owner", getOwner())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
