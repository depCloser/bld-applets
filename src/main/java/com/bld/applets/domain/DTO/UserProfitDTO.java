package com.bld.applets.domain.DTO;

import com.bld.applets.domain.AppletsOrder;

import java.util.List;

/**
 * @author tyx
 * @title: UserProfit
 * @projectName applets
 * @description: TODO
 * @date 2021/4/20 14:19
 */
public class UserProfitDTO {

    private String totalAmount;

    private Long pilesId;

    private String pilesCode;

    private List<AppletsOrder> orders;

    public String getTotalAmount() {
        return totalAmount;
    }

    public UserProfitDTO setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Long getPilesId() {
        return pilesId;
    }

    public UserProfitDTO setPilesId(Long pilesId) {
        this.pilesId = pilesId;
        return this;
    }

    public String getPilesCode() {
        return pilesCode;
    }

    public UserProfitDTO setPilesCode(String pilesCode) {
        this.pilesCode = pilesCode;
        return this;
    }

    public List<AppletsOrder> getOrders() {
        return orders;
    }

    public UserProfitDTO setOrders(List<AppletsOrder> orders) {
        this.orders = orders;
        return this;
    }

}
