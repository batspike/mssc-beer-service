package com.samcancode.msscbeerservice.web.model;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;

@JsonTest
class BeerDtoTest extends BaseTest {

	@Test
	void testSerializeDto() throws JsonProcessingException {
		BeerDto beerDto = getSampleBeerDto();
		String jsonString = objectMapper.writeValueAsString(beerDto);
		System.out.println(jsonString);
	}
	
	@Test
	void testDeserialize() throws IOException {
		String json = "{\"id\":null,\"version\":null,\"createdDate\":\"2020-07-18\",\"lastModifiedDate\":\"2020-07-18T22:11:57.2796979+08:00\",\"beerName\":\"Sample Beer\",\"beerStyle\":\"ALE\",\"upc\":\"123200001\",\"price\":\"12.99\",\"quantityOnHand\":null}";
		
		BeerDto dto = objectMapper.readValue(json, BeerDto.class);
		System.out.println("CreatedDate: "+ dto.getCreatedDate() + " , beerName: " + dto.getBeerName());
	}

}
