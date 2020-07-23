package com.samcancode.msscbeerservice.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.service.inventory.BeerInventoryService;
import com.samcancode.msscbeerservice.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {
	private BeerInventoryService beerInventoryService;
	private BeerMapper mapper;
	
	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}
	
	@Autowired
	public void setMapper(BeerMapper mapper) { this.mapper = mapper; }

	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		BeerDto dto = mapper.beerToBeerDto(beer);
		return dto;
	}
	
	@Override
	public BeerDto beerToBeerDtoWithInventory(Beer beer) {
		BeerDto dto = mapper.beerToBeerDto(beer);
		dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
		return dto;
	}

	@Override
	public Beer beerDtoToBeer(BeerDto beerDto) {
		return mapper.beerDtoToBeer(beerDto);
	}

}
