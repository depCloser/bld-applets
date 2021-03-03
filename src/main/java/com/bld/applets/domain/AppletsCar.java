package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户车辆对象 applets_car
 * 
 * @author tyx
 * @date 2021-03-03
 */
public class AppletsCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String busNumber;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String photo;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public AppletsCar setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsCar setBrand(String brand)
    {
        this.brand = brand;
        return this;
    }

    public String getBrand() 
    {
        return brand;
    }
    public AppletsCar setColor(String color)
    {
        this.color = color;
        return this;
    }

    public String getColor() 
    {
        return color;
    }
    public AppletsCar setBusNumber(String busNumber)
    {
        this.busNumber = busNumber;
        return this;
    }

    public String getBusNumber() 
    {
        return busNumber;
    }
    public AppletsCar setPhoto(String photo)
    {
        this.photo = photo;
        return this;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public AppletsCar setUserId(Long userId)
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
            .append("brand", getBrand())
            .append("color", getColor())
            .append("busNumber", getBusNumber())
            .append("photo", getPhoto())
            .append("userId", getUserId())
            .toString();
    }
}
