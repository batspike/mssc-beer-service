package com.samcancode.msscbeerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.repository.BeerRepository;
import com.samcancode.msscbeerservice.web.mapper.BeerMapper;
import com.samcancode.msscbeerservice.web.model.BeerDto;
import com.samcancode.msscbeerservice.web.model.BeerPagedList;
import com.samcancode.msscbeerservice.web.model.BeerStyleEnum;

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
        if(beer == null) return null;

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepo.save(beer));	}

	@Override
	public void deleteBeerById(UUID beerId) {
		beerRepo.deleteById(beerId);
	}

	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {
		BeerPagedList beerPagedList;
		Page<Beer> beerPage;
		
		if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search both
			beerPage = beerRepo.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		}
		else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			// search beer_service name
			beerPage = beerRepo.findAllByBeerName(beerName, pageRequest);
		}
		else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search beer_service style
			beerPage = beerRepo.findAllByBeerStyle(beerStyle, pageRequest);
		}
		else {
			beerPage = beerRepo.findAll(pageRequest);
		}
		
		beerPagedList = new BeerPagedList(
								beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
								PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()), beerPage.getTotalElements()
							);
		
		return beerPagedList;
	}
	
	

}
