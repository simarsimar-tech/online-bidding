package com.demo.onlinebidding.repository;

import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.SearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBiddingRepository {
    List<Item> find(SearchCriteria searchCriteria);
}

