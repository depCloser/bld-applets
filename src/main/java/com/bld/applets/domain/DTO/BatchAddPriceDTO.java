package com.bld.applets.domain.DTO;

import java.util.List;

/**
 * @author tyx
 * @title: BatchAddPriceDTO
 * @projectName applets
 * @description: TODO
 * @date 2021/4/7 16:21
 */
public class BatchAddPriceDTO {

    private List<ChargingPriceDTO> list;

    private Integer type;

    private Long pilesId;

    public List<ChargingPriceDTO> getList() {
        return list;
    }

    public BatchAddPriceDTO setList(List<ChargingPriceDTO> list) {
        this.list = list;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public BatchAddPriceDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getPilesId() {
        return pilesId;
    }

    public BatchAddPriceDTO setPilesId(Long pilesId) {
        this.pilesId = pilesId;
        return this;
    }

    @Override
    public String toString() {
        return "BatchAddPriceDTO{" +
                "list=" + list.get(0).toString() +
                ", type=" + type +
                ", pilesId=" + pilesId +
                '}';
    }
}
