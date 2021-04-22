package com.bld.applets.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;

    public Date getCreateTime()
    {
        return createTime;
    }

    public BaseEntity setCreateTime(Date createTime)
    {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public BaseEntity setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
        return this;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public BaseEntity setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
        return this;
    }

    public String getRemark()
    {
        return remark;
    }

    public BaseEntity setRemark(String remark)
    {
        this.remark = remark;
        return this;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public BaseEntity setParams(Map<String, Object> params)
    {
        this.params = params;
        return this;
    }

}
