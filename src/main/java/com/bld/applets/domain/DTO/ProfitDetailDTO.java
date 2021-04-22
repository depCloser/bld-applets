package com.bld.applets.domain.DTO;

import com.bld.applets.domain.AppletsOrder;

import java.util.Date;

/**
 * @author tyx
 * @title: ProfitDetailDTO
 * @projectName applets
 * @description: TODO
 * @date 2021/4/20 14:24
 */
public class ProfitDetailDTO {

    private Date time;

    private String pilesCode;

    private String cost;

    private String payer;

    private AppletsOrder order;

    public Date getTime() {
        return time;
    }

    public ProfitDetailDTO setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getPilesCode() {
        return pilesCode;
    }

    public ProfitDetailDTO setPilesCode(String pilesCode) {
        this.pilesCode = pilesCode;
        return this;
    }

    public String getCost() {
        return cost;
    }

    public ProfitDetailDTO setCost(String cost) {
        this.cost = cost;
        return this;
    }

    public String getPayer() {
        return payer;
    }

    public ProfitDetailDTO setPayer(String payer) {
        this.payer = payer;
        return this;
    }

    public AppletsOrder getOrder() {
        return order;
    }

    public ProfitDetailDTO setOrder(AppletsOrder order) {
        this.order = order;
        return this;
    }

}
