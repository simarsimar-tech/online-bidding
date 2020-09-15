package com.demo.onlinebidding.service;

import com.demo.onlinebidding.model.BiddingRequest;
import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.SearchCriteria;
import com.demo.onlinebidding.repository.BiddingRepository;
import com.demo.onlinebidding.repository.CustomBiddingRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class BiddingService {

    private CustomBiddingRepository customBiddingRepository;

    private BiddingRepository biddingRepository;

    @Inject
    public BiddingService(CustomBiddingRepository customBiddingRepository, BiddingRepository biddingRepository) {
        this.customBiddingRepository = customBiddingRepository;
        this.biddingRepository = biddingRepository;
    }

    public Item placeBid(String itemCode, BiddingRequest biddingRequest) {
        Optional<Item> item = biddingRepository.findById(itemCode);
        return null;
    }

    public List<Item> search(SearchCriteria criteria) {
        return customBiddingRepository.find(criteria);
    }
}
