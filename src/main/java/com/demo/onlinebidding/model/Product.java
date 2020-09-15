package com.demo.onlinebidding.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {

    private String itemCode;
    private Double basePrice;
    private Double stepRate;
    private String status;
    private List<BidEvents> bidEvents;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getStepRate() {
        return stepRate;
    }

    public void setStepRate(Double stepRate) {
        this.stepRate = stepRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BidEvents> getBidEvents() {
        if(bidEvents == null) {
            return new ArrayList<>();
        }
        return bidEvents;
    }

    public void setBidEvents(List<BidEvents> bidEvents) {
        this.bidEvents = bidEvents;
    }
}
