package com.samcancode.msscbeerservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.web.model.BeerStyleEnum;

public interface BeerRepository extends PagingAndSortingRepository<Beer,UUID>{
	Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
	Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
}
