package com.demo.onlinebidding.repository;

import com.demo.onlinebidding.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BiddingRepository extends PagingAndSortingRepository<Item, String> {

}
