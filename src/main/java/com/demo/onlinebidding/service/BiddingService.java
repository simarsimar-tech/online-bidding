package com.demo.onlinebidding.service;

import com.demo.onlinebidding.exception.RejectedException;
import com.demo.onlinebidding.exception.ResourceNotFoundException;
import com.demo.onlinebidding.model.BidStatus;
import com.demo.onlinebidding.model.BiddingRequest;
import com.demo.onlinebidding.model.Event;
import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.SearchCriteria;
import com.demo.onlinebidding.repository.BiddingRepository;

import com.demo.onlinebidding.repository.EventRepository;
import com.demo.onlinebidding.validation.RequestValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BiddingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiddingService.class);

    private BiddingRepository biddingRepository;

    private EventRepository eventRepository;

    private RequestValidator requestValidator;

    @Inject
    public BiddingService(BiddingRepository biddingRepository,
                          EventRepository eventRepository, RequestValidator requestValidator) {
        this.biddingRepository = biddingRepository;
        this.eventRepository = eventRepository;
        this.requestValidator = requestValidator;
    }

    public void placeBid(String itemCode, BiddingRequest biddingRequest, String user) {
        Optional<Item> response = biddingRepository.findById(itemCode);

        if(response == null || response.get() == null) {
            throw new ResourceNotFoundException();
        }
        Item item = response.get();

        try {
            requestValidator.validateBiddingRequest(item, biddingRequest);

            item.setCurrentPrice(biddingRequest.getAmount());
            biddingRepository.save(item);
            saveEvent(item, biddingRequest.getAmount(), BidStatus.ACCEPTED.getLabel(), user);
        } catch(RejectedException e) {
            LOGGER.error("Bid rejected for itemCode {}", itemCode, e);
            saveEvent(item, biddingRequest.getAmount(), BidStatus.REJECTED.getLabel(), user);
            throw e;
        }
    }

    public List<Item> search(SearchCriteria criteria) {
        return find(criteria);
    }

    private void saveEvent(Item item, Double currentPrice, String status, String user) {
        Event event = new Event();
        event.setBiddingPrice(currentPrice);
        event.setItem(item);
        event.setStatus(status);
        event.setUser(user);

        eventRepository.save(event);
    }

    private List<Item> find(SearchCriteria searchCriteria) {
        Pageable paging = PageRequest.of(searchCriteria.getStart(), searchCriteria.getCount(), Sort.by("updated_date"));

        Page<Item> pagedResult = biddingRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        return Collections.emptyList();
    }
}
