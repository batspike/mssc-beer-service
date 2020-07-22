package com.samcancode.msscbeerservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.samcancode.msscbeerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer,UUID>{

}
