package com.demo.onlinebidding.controller;

import com.demo.onlinebidding.model.BiddingRequest;
import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.SearchCriteria;
import com.demo.onlinebidding.service.BiddingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javax.inject.Inject;
import java.util.List;

@Validated
@Controller
@RequestMapping(value = "/v1/auction", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
public class BiddingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiddingController.class);

    private final BiddingService biddingService;

    @Inject
    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @RequestMapping(value = "/{itemCode}/bid", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Place a bidding on item", response = Item.class, responseContainer = "ResponseEntity")
    @ApiResponses({
            @ApiResponse(code = 200, message = "<b>Retrieve</b>, indicating that the submitted retrieval request has succeeded. The response HTTP header <i>Location</i> contains the resource identifier."),
            @ApiResponse(code = 404, message = "<b>Not Found</b> : the resource does not exist.")
    })
    public ResponseEntity<Item> placeBid(@PathVariable(name = "itemCode") final String itemCode,
            @RequestBody @Valid @NotNull BiddingRequest biddingRequest,
                                         Authentication authentication) {

        LOGGER.debug("placeBid called on itemCode {}", itemCode);

        biddingService.placeBid(itemCode, biddingRequest, authentication.getName());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Search auctions", response = Item.class, responseContainer = "ResponseEntity")
    @ApiResponses({
            @ApiResponse(code = 200, message = "<b>Retrieve</b>, indicating that the submitted retrieval request has succeeded. The response HTTP header <i>Location</i> contains the resource identifier."),
            @ApiResponse(code = 404, message = "<b>Not Found</b> : the resource does not exist.")
    })
    public ResponseEntity<List<Item>> searchAuctions(@RequestParam(name = "status") final String status,
                                               @RequestParam(value = "start", required = false) @Min(value = 0, message = "must be positive") Integer start,
                                               @RequestParam(value = "count", required = false) @Min(value = 0, message = "must be positive") Integer count) {

        LOGGER.debug("searchAuctions called for status {}", status);

        SearchCriteria criteria =  SearchCriteria.builder()
                .status(status)
                .start(start == null ? 0 : start)
                .count(count == null ? 10 : count)
                .build();

        List<Item> items = biddingService.search(criteria);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
