package com.demo.onlinebidding.controller;

import com.demo.onlinebidding.model.BiddingRequest;
import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.SearchCriteria;
import com.demo.onlinebidding.service.BiddingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class BiddingControllerTest {

    @Mock
    private BiddingService biddingService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BiddingController biddingController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        biddingController = new BiddingController(biddingService);
    }

    @Test
    public void placeBid_success() {

        // stub
        doReturn(new Item()).when(biddingService).placeBid(anyString(), any(BiddingRequest.class), anyString());

        // invoke
        ResponseEntity<Item> responseEntity = biddingController.placeBid("CODE_01", new BiddingRequest(), authentication);

        //verify
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchAuctions_success() {

        // stub
        doReturn(new Item()).when(biddingService).search(any(SearchCriteria.class));

        // invoke
        ResponseEntity<List<Item>> responseEntity = biddingController.searchAuctions("status", 0, 10);

        //verify
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
