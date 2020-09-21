package com.demo.onlinebidding.validation;

import com.demo.onlinebidding.exception.InvalidStatusException;
import com.demo.onlinebidding.exception.RejectedException;
import com.demo.onlinebidding.model.BiddingRequest;
import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.ItemStatus;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RequestValidator {

    public void validateBiddingRequest(Item item, BiddingRequest biddingRequest) {
        if(!StringUtils.isEmpty(item.getStatus()) &&
                ItemStatus.OVER.getLabel().equals(item.getStatus().toLowerCase())) {
            throw new RejectedException("Bidding for this item is over");
        }

        if(biddingRequest.getBidAmount() < item.getBasePrice()) {
            throw new RejectedException("Bidding amount should be greater than current price");
        }

        if(item.getCurrentPrice() != null && ((biddingRequest.getBidAmount() - item.getCurrentPrice()) < item.getStepRate())) {
            throw new RejectedException("Bidding amount not acceptable");
        }
    }

    public void validateStatus(String status) {
        if(!StringUtils.isEmpty(status) && ItemStatus.getValue(status) == null) {
            throw new InvalidStatusException("Invalid status in search");
        }

    }
}
