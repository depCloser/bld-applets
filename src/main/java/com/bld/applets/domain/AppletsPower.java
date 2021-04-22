package com.bld.applets.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功率图对象 applets_power
 * 
 * @author tyx
 * @date 2021-04-12
 */
public class AppletsPower extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    private Long pId;

    /** 功率kw */
    private String power;

    /** 电量度 kwh */
    private String electricity;

    /** 时间点 */
    private String time;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public AppletsPower setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsPower setpId(Long pId)
    {
        this.pId = pId;
        return this;
    }

    public Long getpId() 
    {
        return pId;
    }
    public AppletsPower setPower(String power)
    {
        this.power = power;
        return this;
    }

    public String getPower() 
    {
        return power;
    }
    public AppletsPower setElectricity(String electricity)
    {
        this.electricity = electricity;
        return this;
    }

    public String getElectricity() 
    {
        return electricity;
    }
    public AppletsPower setTime(String time)
    {
        this.time = time;
        return this;
    }

    public String getTime() 
    {
        return time;
    }
    public AppletsPower setDate(Date date)
    {
        this.date = date;
        return this;
    }

    public Date getDate() 
    {
        return date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pId", getpId())
            .append("power", getPower())
            .append("electricity", getElectricity())
            .append("time", getTime())
            .append("date", getDate())
            .toString();
    }
}
