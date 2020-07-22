package com.samcancode.msscbeerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.repository.BeerRepository;
import com.samcancode.msscbeerservice.web.mapper.BeerMapper;
import com.samcancode.msscbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepo;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto findFirstBeer() {
		List<BeerDto> beers = new ArrayList<>();
		beerRepo.findAll().forEach(beer -> beers.add(beerMapper.beerToBeerDto(beer)));
		
		return beers.get(0);
	}

	@Override
	public BeerDto findBeerById(UUID beerId) {
		return beerMapper.beerToBeerDto(beerRepo.findById(beerId).orElse(null));
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepo.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeerById(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepo.findById(beerId).orElse(null);
		if (beer == null) {
			return null;
		}
		else {
			beer = beerRepo.save(beerMapper.beerDtoToBeer(beerDto));
			return beerMapper.beerToBeerDto(beer);
		}
	}

	@Override
	public void deleteBeerById(UUID beerId) {
		beerRepo.deleteById(beerId);
	}
	
	

}
