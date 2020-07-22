package com.samcancode.msscbeerservice.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import com.samcancode.msscbeerservice.web.model.BeerDto;
import com.samcancode.msscbeerservice.web.model.BeerPagedList;
import com.samcancode.msscbeerservice.web.model.BeerStyleEnum;

public interface BeerService {
	BeerDto findFirstBeer();
	BeerDto findBeerById(UUID beerId);
	BeerDto saveBeer(BeerDto beer);
	BeerDto updateBeerById(UUID beerId, BeerDto beerDto);
	void deleteBeerById(UUID beerId);
	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);
}
