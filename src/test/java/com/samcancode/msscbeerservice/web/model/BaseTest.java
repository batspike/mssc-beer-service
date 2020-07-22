package com.samcancode.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samcancode.msscbeerservice.bootstrap.BeerLoader;

public class BaseTest {
	@Autowired
	ObjectMapper objectMapper;
	

	BeerDto getSampleBeerDto() {
		return BeerDto.builder()
						.beerName("Sample Beer")
						.beerStyle(BeerStyleEnum.ALE)
						.createdDate(OffsetDateTime.now())
						.lastModifiedDate(OffsetDateTime.now())
						.price(new BigDecimal("12.99"))
						.upc(BeerLoader.BEER_1_UPC)
						.build();
	}
}
