package com.demo.onlinebidding.repository.impl;

import com.demo.onlinebidding.model.Item;
import com.demo.onlinebidding.model.SearchCriteria;
import com.demo.onlinebidding.repository.BiddingRepository;
import com.demo.onlinebidding.repository.CustomBiddingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class BiddingRepositoryImpl implements CustomBiddingRepository {

    private BiddingRepository biddingRepository;

    @Inject
    public BiddingRepositoryImpl(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }

    @Override
    public List<Item> find(SearchCriteria searchCriteria) {
        Pageable paging = PageRequest.of(searchCriteria.getStart(), searchCriteria.getCount(), Sort.by("updated_date"));

        Page<Item> pagedResult = biddingRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        return Collections.emptyList();
    }
}
