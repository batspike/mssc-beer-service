package com.samcancode.msscbeerservice.web.mapper;

import org.mapstruct.Mapper;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.web.model.BeerDto;

@Mapper(uses= {DateMapper.class})
public interface BeerMapper {
	BeerDto beerToBeerDto(Beer beer);
	Beer    beerDtoToBeer(BeerDto beerDto);
}
