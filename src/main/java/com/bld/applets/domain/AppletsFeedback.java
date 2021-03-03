package com.bld.applets.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 反馈对象 applets_feedback
 * 
 * @author tyx
 * @date 2021-03-03
 */
public class AppletsFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 用户id */
    @Excel(name = "用户id")
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
    public AppletsFeedback setTime(Date time)
    {
        this.time = time;
        return this;
    }

    public Date getTime() 
    {
        return time;
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
            .append("time", getTime())
            .append("userId", getUserId())
            .toString();
    }
}
