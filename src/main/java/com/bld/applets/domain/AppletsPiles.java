package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 充电桩对象 applets_piles
 * 
 * @author tyx
 * @date 2021-03-10
 */
public class AppletsPiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    private String name;

    /** 编号 */
    private String code;

    /** 类型：1社区、0公共 */
    private Long type;

    /** 状态0维护、1正常 */
    private Long status;

    /** 充电状态：0可以充电、1正在充电、2重启 */
    private Long chargedState;

    /** 充电方式：1直流快充 */
    private Long chargedType;

    /** 省份 */
    private String addrProvince;

    /** 市 */
    private String addrCity;

    /** 区 */
    private String addrDistrict;

    /** 详细地址 */
    private String addrDetail;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 属主id */
    private Long userId;

    /** 累计充电量 */
    private Long chargedNum;

    /** 功率 */
    private String power;

    public AppletsPiles setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsPiles setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getName() 
    {
        return name;
    }
    public AppletsPiles setCode(String code)
    {
        this.code = code;
        return this;
    }

    public String getCode() 
    {
        return code;
    }
    public AppletsPiles setType(Long type)
    {
        this.type = type;
        return this;
    }

    public Long getType() 
    {
        return type;
    }
    public AppletsPiles setStatus(Long status)
    {
        this.status = status;
        return this;
    }

    public Long getStatus() 
    {
        return status;
    }
    public AppletsPiles setChargedState(Long chargedState)
    {
        this.chargedState = chargedState;
        return this;
    }

    public Long getChargedState() 
    {
        return chargedState;
    }
    public AppletsPiles setAddrProvince(String addrProvince)
    {
        this.addrProvince = addrProvince;
        return this;
    }

    public String getAddrProvince() 
    {
        return addrProvince;
    }
    public AppletsPiles setAddrCity(String addrCity)
    {
        this.addrCity = addrCity;
        return this;
    }

    public String getAddrCity() 
    {
        return addrCity;
    }
    public AppletsPiles setAddrDistrict(String addrDistrict)
    {
        this.addrDistrict = addrDistrict;
        return this;
    }

    public String getAddrDistrict() 
    {
        return addrDistrict;
    }
    public AppletsPiles setAddrDetail(String addrDetail)
    {
        this.addrDetail = addrDetail;
        return this;
    }

    public String getAddrDetail() 
    {
        return addrDetail;
    }
    public AppletsPiles setLongitude(String longitude)
    {
        this.longitude = longitude;
        return this;
    }

    public String getLongitude()
    {
        return longitude;
    }
    public AppletsPiles setLatitude(String latitude)
    {
        this.latitude = latitude;
        return this;
    }

    public String getLatitude()
    {
        return latitude;
    }
    public AppletsPiles setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public String getPower() {
        return power;
    }

    public AppletsPiles setPower(String power) {
        this.power = power;
        return this;
    }

    public Long getChargedType() {
        return chargedType;
    }

    public AppletsPiles setChargedType(Long chargedType) {
        this.chargedType = chargedType;
        return this;
    }

    public Long getChargedNum() {
        return chargedNum;
    }

    public AppletsPiles setChargedNum(Long chargedNum) {
        this.chargedNum = chargedNum;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("type", getType())
            .append("status", getStatus())
            .append("chargedState", getChargedState())
            .append("addrProvince", getAddrProvince())
            .append("addrCity", getAddrCity())
            .append("addrDistrict", getAddrDistrict())
            .append("addrDetail", getAddrDetail())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("power", getPower())
            .append("chargedType", getChargedType())
            .append("chargedNum", getChargedNum())
            .toString();
    }

}
