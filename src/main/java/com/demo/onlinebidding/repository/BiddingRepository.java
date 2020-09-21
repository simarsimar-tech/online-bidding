package com.demo.onlinebidding.repository;

import com.demo.onlinebidding.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BiddingRepository extends PagingAndSortingRepository<Item, String> {

    List<Item> findAllByStatus(String status, Pageable pageable);
}
