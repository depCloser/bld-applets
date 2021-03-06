package com.bld.applets.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评论对象 applets_comment
 * 
 * @author tyx
 * @date 2021-03-03
 */
public class AppletsComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 评论内容 */
    private String content;

    /** 评论时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    /** 星级 */
    private Integer level;

    /** 标签 */
    private String tag;

    /** 用户id */
    private Long userId;

    /** 充电桩id */
    private Long pilesId;

    private AppletsPiles piles;

    public AppletsComment setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsComment setContent(String content)
    {
        this.content = content;
        return this;
    }

    public String getContent() 
    {
        return content;
    }
    public AppletsComment setTime(Date time)
    {
        this.time = time;
        return this;
    }

    public Date getTime() 
    {
        return time;
    }
    public AppletsComment setLevel(Integer level)
    {
        this.level = level;
        return this;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public AppletsComment setTag(String tag)
    {
        this.tag = tag;
        return this;
    }

    public String getTag() 
    {
        return tag;
    }
    public AppletsComment setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public AppletsComment setPilesId(Long pilesId)
    {
        this.pilesId = pilesId;
        return this;
    }

    public Long getPilesId() 
    {
        return pilesId;
    }

    public AppletsPiles getPiles() {
        return piles;
    }

    public AppletsComment setPiles(AppletsPiles piles) {
        this.piles = piles;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("time", getTime())
            .append("level", getLevel())
            .append("tag", getTag())
            .append("userId", getUserId())
            .append("pilesId", getPilesId())
            .toString();
    }
}
