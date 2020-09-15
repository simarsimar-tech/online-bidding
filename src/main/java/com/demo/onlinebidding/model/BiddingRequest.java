package com.demo.onlinebidding.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BiddingRequest {

    @NotNull
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
