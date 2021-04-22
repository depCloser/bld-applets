package com.bld.applets.domain.DTO;

/**
 * @author tyx
 * @title: ChargingPriceDTO
 * @projectName applets
 * @description: TODO
 * @date 2021/3/29 16:14
 */
public class ChargingPriceDTO {

    private Long id;
    private String startTime;
    private String endTime;
    private int interval;
    private String price;

    public Long getId() {
        return id;
    }

    public ChargingPriceDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public int getInterval() {
        return interval;
    }

    public ChargingPriceDTO setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ChargingPriceDTO setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public ChargingPriceDTO setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public ChargingPriceDTO setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public String toString() {
        return "ChargingPriceDTO{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", interval=" + interval +
                ", price='" + price + '\'' +
                '}';
    }
}
