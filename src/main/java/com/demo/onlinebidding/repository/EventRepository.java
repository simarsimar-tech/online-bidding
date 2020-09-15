package com.demo.onlinebidding.repository;

import com.demo.onlinebidding.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
