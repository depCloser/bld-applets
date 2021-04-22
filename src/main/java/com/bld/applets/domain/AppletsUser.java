package com.bld.applets.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户对象 applets_user
 * 
 * @author tyx
 * @date 2021-03-10
 */
public class AppletsUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    private String name;

    /** 电话号码 */
    private String phone;

    /** 会员等级 */
    private Integer level;

    /** 是否拥有充电桩：1是、0否 */
    private Integer owner;

    /** 积分 */
    private Long integral;

    /** 余额 */
    private String balance;

    /** 头像地址 */
    private String photo;

    /** 注册标识码 */
    private String code;

    /** 性别 0女 1男 **/
    private Integer sex;

    private String sessionKey;

    private Integer status;

    private Integer isAuth;

    private Long monthCharge;

    private Long memberDifference;

    public AppletsUser setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Long getId() 
    {
        return id;
    }
    public AppletsUser setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getName() 
    {
        return name;
    }
    public AppletsUser setPhone(String phone)
    {
        this.phone = phone;
        return this;
    }

    public String getPhone() 
    {
        return phone;
    }
    public AppletsUser setLevel(Integer level)
    {
        this.level = level;
        return this;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public AppletsUser setOwner(Integer owner)
    {
        this.owner = owner;
        return this;
    }

    public Integer getOwner() 
    {
        return owner;
    }
    public AppletsUser setIntegral(Long integral)
    {
        this.integral = integral;
        return this;
    }

    public Long getIntegral() 
    {
        return integral;
    }
    public AppletsUser setBalance(String balance)
    {
        this.balance = balance;
        return this;
    }

    public String getBalance() 
    {
        return balance;
    }
    public AppletsUser setPhoto(String photo)
    {
        this.photo = photo;
        return this;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public AppletsUser setCode(String code)
    {
        this.code = code;
        return this;
    }

    public String getCode() 
    {
        return code;
    }

    public Long getMonthCharge() {
        return monthCharge;
    }

    public AppletsUser setMonthCharge(Long monthCharge) {
        this.monthCharge = monthCharge;
        return this;
    }

    public Long getMemberDifference() {
        return memberDifference;
    }

    public AppletsUser setMemberDifference(Long memberDifference) {
        this.memberDifference = memberDifference;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public AppletsUser setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public AppletsUser setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AppletsUser setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public AppletsUser setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("level", getLevel())
            .append("owner", getOwner())
            .append("integral", getIntegral())
            .append("balance", getBalance())
            .append("photo", getPhoto())
            .append("code", getCode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("sex", getSex())
            .append("status", getStatus())
            .append("sessionKey", getSessionKey())
            .append("isAuth", getIsAuth())
            .toString();
    }
}
