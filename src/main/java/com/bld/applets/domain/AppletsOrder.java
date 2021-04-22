package com.bld.applets.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单对象 applets_order
 * 
 * @author tyx
 * @date 2021-03-10
 */
public class AppletsOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 订单编号 */
    private String code;

    /** 订单状态：0待支付、1已支付、2已完成 */
    private Integer status;

    /** 订单类型：1充电订单 2充值订单 */
    private Integer type;

    /** 订单描述 */
    private String desc;

    /** 用户id */
    private Long userId;

    /** 用户昵称 */
    private String userName;

    /** 充电桩id */
    private Long pilesId;

    /** 充电开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 充电结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 花费金额 */
    private String cost;

    /** 充电量 */
    private Long charge;

    /** 积分 */
    private Long integral;

    /** 花费金额 */
    private String prepayId;

    /** 支付类型 1微信 2余额 */
    private Integer paymentType;

    /** 备注 */
    private String remark;

    /** 微信备注 */
    private String callBack;

    private AppletsPiles piles;

    public AppletsOrder setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsOrder setCode(String code)
    {
        this.code = code;
        return this;
    }

    public String getCode() 
    {
        return code;
    }
    public AppletsOrder setStatus(Integer status)
    {
        this.status = status;
        return this;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public AppletsOrder setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public AppletsOrder setPilesId(Long pilesId)
    {
        this.pilesId = pilesId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AppletsOrder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getPilesId()
    {
        return pilesId;
    }
    public AppletsOrder setStartTime(Date startTime)
    {
        this.startTime = startTime;
        return this;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public AppletsOrder setEndTime(Date endTime)
    {
        this.endTime = endTime;
        return this;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public AppletsOrder setCost(String cost)
    {
        this.cost = cost;
        return this;
    }

    public String getCost() 
    {
        return cost;
    }
    public AppletsOrder setCharge(Long charge)
    {
        this.charge = charge;
        return this;
    }

    public Long getCharge() 
    {
        return charge;
    }
    public AppletsOrder setIntegral(Long integral)
    {
        this.integral = integral;
        return this;
    }

    public Long getIntegral() 
    {
        return integral;
    }

    public AppletsPiles getPiles() {
        return piles;
    }

    public AppletsOrder setPiles(AppletsPiles piles) {
        this.piles = piles;
        return this;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public AppletsOrder setPrepayId(String prepayId) {
        this.prepayId = prepayId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public AppletsOrder setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public AppletsOrder setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public AppletsOrder setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public AppletsOrder setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getCallBack() {
        return callBack;
    }

    public AppletsOrder setCallBack(String callBack) {
        this.callBack = callBack;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("status", getStatus())
            .append("type", getType())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("pilesId", getPilesId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("cost", getCost())
            .append("charge", getCharge())
            .append("integral", getIntegral())
            .append("prepayId", getPrepayId())
            .append("paymentType", getPaymentType())
            .append("desc", getDesc())
            .append("remark", getRemark())
            .append("callBack", getCallBack())
            .toString();
    }

}
