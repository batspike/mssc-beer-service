package com.samcancode.msscbeerservice.service;

import java.util.UUID;

import com.samcancode.msscbeerservice.web.model.BeerDto;

public interface BeerService {
	BeerDto findFirstBeer();
	BeerDto findBeerById(UUID beerId);
	BeerDto saveBeer(BeerDto beer);
	BeerDto updateBeerById(UUID beerId, BeerDto beerDto);
	void deleteBeerById(UUID beerId);
}
