package com.samcancode.msscbeerservice.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;

@ActiveProfiles("snake")
@JsonTest
class BeerDtoSnakeTest extends BaseTest {

	@Test
	void testKebab() throws JsonProcessingException {
		BeerDto dto = getSampleBeerDto();
		String json = objectMapper.writeValueAsString(dto);
		System.out.println("SNAKE_CASE: "+json);
	}

}
