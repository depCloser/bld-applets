package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电桩电价对象 applets_electrovalence
 * 
 * @author tyx
 * @date 2021-03-03
 */
public class AppletsElectrovalence extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 时段：0低估、1平峰、2高峰 */
    @Excel(name = "时段：0低估、1平峰、2高峰")
    private Integer timeFrame;

    /** 价格/每度 */
    @Excel(name = "价格/每度")
    private String price;

    /** 价格类型：0卖出、1买入 */
    @Excel(name = "价格类型：0卖出、1买入")
    private Integer type;

    /** 充电桩id */
    @Excel(name = "充电桩id")
    private Long pilesId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    private AppletsPiles piles;

    public AppletsElectrovalence setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsElectrovalence setStartTime(String startTime)
    {
        this.startTime = startTime;
        return this;
    }

    public String getStartTime() 
    {
        return startTime;
    }
    public AppletsElectrovalence setEndTime(String endTime)
    {
        this.endTime = endTime;
        return this;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public AppletsElectrovalence setTimeFrame(Integer timeFrame)
    {
        this.timeFrame = timeFrame;
        return this;
    }

    public Integer getTimeFrame() 
    {
        return timeFrame;
    }
    public AppletsElectrovalence setPrice(String price)
    {
        this.price = price;
        return this;
    }

    public String getPrice() 
    {
        return price;
    }
    public AppletsElectrovalence setType(Integer type)
    {
        this.type = type;
        return this;
    }

    public Integer getType() 
    {
        return type;
    }
    public AppletsElectrovalence setPilesId(Long pilesId)
    {
        this.pilesId = pilesId;
        return this;
    }

    public Long getPilesId() 
    {
        return pilesId;
    }
    public AppletsElectrovalence setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public AppletsPiles getPiles() {
        return piles;
    }

    public AppletsElectrovalence setPiles(AppletsPiles piles) {
        this.piles = piles;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("timeFrame", getTimeFrame())
            .append("price", getPrice())
            .append("type", getType())
            .append("pilesId", getPilesId())
            .append("userId", getUserId())
            .toString();
    }
}
