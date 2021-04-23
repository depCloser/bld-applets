package com.bld.applets.domain.DTO;

/**
 * @author tyx
 * @title: QueryPilesDTO
 * @projectName applets
 * @description: TODO
 * @date 2021/4/22 16:35
 */
public class QueryPilesDTO {

    /** 类型：1社区、0公共 */
    private Long type;

    /** 状态0维护、1正常 */
    private Long status;

    /** 充电状态：0可以充电、1正在充电、2重启 */
    private Long chargedState;

    /** 充电桩名称 */
    private String name;

    /** 客户端经度 */
    private String sourceJ;

    /** 客户端纬度 */
    private String sourceW;

    /** 距离 */
    private Double distance;

    private Long userId;

    public Long getType() {
        return type;
    }

    public QueryPilesDTO setType(Long type) {
        this.type = type;
        return this;
    }

    public Long getStatus() {
        return status;
    }

    public QueryPilesDTO setStatus(Long status) {
        this.status = status;
        return this;
    }

    public Long getChargedState() {
        return chargedState;
    }

    public QueryPilesDTO setChargedState(Long chargedState) {
        this.chargedState = chargedState;
        return this;
    }

    public String getSourceJ() {
        return sourceJ;
    }

    public QueryPilesDTO setSourceJ(String sourceJ) {
        this.sourceJ = sourceJ;
        return this;
    }

    public String getSourceW() {
        return sourceW;
    }

    public QueryPilesDTO setSourceW(String sourceW) {
        this.sourceW = sourceW;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public QueryPilesDTO setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public String getName() {
        return name;
    }

    public QueryPilesDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QueryPilesDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "QueryPilesDTO{" +
                "type=" + type +
                ", status=" + status +
                ", chargedState=" + chargedState +
                ", name='" + name + '\'' +
                ", sourceJ='" + sourceJ + '\'' +
                ", sourceW='" + sourceW + '\'' +
                ", distance=" + distance +
                ", userId=" + userId +
                '}';
    }

}
