package com.demo.onlinebidding.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class Item {

    @Id
    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "base_price")
    @NotNull
    private Double basePrice;

    @Column(name = "step_rate")
    @NotNull
    private Double stepRate;

    @NotBlank
    private String status;

    @Column(name = "current_price")
    @NotNull
    private Double currentPrice;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Event> bidEvents;

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

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<Event> getBidEvents() {
        if(bidEvents == null) {
            return new ArrayList<>();
        }
        return bidEvents;
    }

    public void setBidEvents(List<Event> bidEvents) {
        this.bidEvents = bidEvents;
    }
}
