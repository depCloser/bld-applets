package com.bld.applets.domain.DTO;

import java.util.List;

/**
 * @author tyx
 * @title: ElectrovalenceDTO
 * @projectName applets
 * @description: TODO
 * @date 2021/3/29 16:00
 */
public class ElectrovalenceDTO {

    private Long pid;
    private String name;
    private String addr;
    private List<ChargingPriceDTO> priceArray;

    public Long getPid() {
        return pid;
    }

    public ElectrovalenceDTO setPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ElectrovalenceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddr() {
        return addr;
    }

    public ElectrovalenceDTO setAddr(String addr) {
        this.addr = addr;
        return this;
    }

    public List<ChargingPriceDTO> getPriceArray() {
        return priceArray;
    }

    public ElectrovalenceDTO setPriceArray(List<ChargingPriceDTO> priceArray) {
        this.priceArray = priceArray;
        return this;
    }

}
