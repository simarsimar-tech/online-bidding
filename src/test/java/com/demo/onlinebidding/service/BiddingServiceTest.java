package com.demo.onlinebidding.service;

import com.demo.onlinebidding.exception.InvalidStatusException;
import com.demo.onlinebidding.exception.RejectedException;
import com.demo.onlinebidding.exception.ResourceNotFoundException;
import com.demo.onlinebidding.model.BiddingRequest;
import com.demo.onlinebidding.model.Event;
import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.ItemStatus;
import com.demo.onlinebidding.model.SearchCriteria;
import com.demo.onlinebidding.repository.BiddingRepository;
import com.demo.onlinebidding.repository.EventRepository;
import com.demo.onlinebidding.validation.RequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class BiddingServiceTest {

    @InjectMocks
    private BiddingService biddingService;

    @InjectMocks
    private RequestValidator requestValidator;

    @Mock
    private BiddingRepository biddingRepository;

    @Mock
    private EventRepository eventRepository;

    private String ITEM_CODE = "CODE_01";

    private String USER = "user";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        requestValidator = new RequestValidator();
        biddingService = new BiddingService(biddingRepository, eventRepository, requestValidator);
    }

    private BiddingRequest getBiddingRequest(Double amount) {
        BiddingRequest biddingRequest = new BiddingRequest();
        biddingRequest.setBidAmount(amount);
        return biddingRequest;
    }

    private Item getItem() {
        Item item = new Item();
        item.setCurrentPrice(300D);
        item.setBasePrice(250D);
        item.setStepRate(50D);
        return item;
    }

    @Test(expected = ResourceNotFoundException.class)
    public void placeBid_failure_item_not_found() {

        // stub
        doReturn(null).when(biddingRepository).findById(ITEM_CODE);

        // invoke
        biddingService.placeBid(ITEM_CODE, getBiddingRequest(25D), USER);
    }

    @Test(expected = RejectedException.class)
    public void placeBid_failure_bidding_price_less_than_current() {

        Optional<Item> value = Optional.of(getItem());

        // stub
        doReturn(value).when(biddingRepository).findById(ITEM_CODE);

        // invoke
        biddingService.placeBid(ITEM_CODE, getBiddingRequest(25D), USER);
    }

    @Test
    public void placeBid_success() {

        BiddingRequest request = new BiddingRequest();

        Optional<Item> value = Optional.of(getItem());

        // stub
        doReturn(value).when(biddingRepository).findById(ITEM_CODE);

        // invoke
        biddingService.placeBid(ITEM_CODE, getBiddingRequest(400D), USER);

        //verify
        verify(biddingRepository, times(1)).save(value.get());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test(expected = InvalidStatusException.class)
    public void search_bids_failure_invalid_status() {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStatus("invalid");

        // invoke
        biddingService.search(searchCriteria);

    }

    @Test
    public void search_running_bids_success() {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStatus(ItemStatus.RUNNING.getLabel());
        searchCriteria.setStart(0);
        searchCriteria.setCount(10);

        // stub
        doReturn(new ArrayList<>()).when(biddingRepository).findAllByStatus(anyString(), any(Pageable.class));

        // invoke
        biddingService.search(searchCriteria);

        //verify
        verify(biddingRepository, times(1)).findAllByStatus(anyString(), any(Pageable.class));
    }

}
