package com.samcancode.msscbeerservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.web.model.BeerDto;

@Mapper(uses= {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class) //to get on-hand-quantity from inventory service
public interface BeerMapper {
	BeerDto beerToBeerDto(Beer beer);
	Beer    beerDtoToBeer(BeerDto beerDto);
}
