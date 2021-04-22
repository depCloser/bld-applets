package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 反馈对象 applets_feedback
 * 
 * @author tyx
 * @date 2021-03-10
 */
public class AppletsFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 内容 */
    private String content;

    /** 用户id */
    private Long userId;

    public AppletsFeedback setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsFeedback setContent(String content)
    {
        this.content = content;
        return this;
    }

    public String getContent() 
    {
        return content;
    }
    public AppletsFeedback setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
