package com.samcancode.msscbeerservice.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;

@ActiveProfiles("kebab")
@JsonTest
class BeerDtoKebabTest extends BaseTest {

	@Test
	void testKebab() throws JsonProcessingException {
		BeerDto dto = getSampleBeerDto();
		String json = objectMapper.writeValueAsString(dto);
		System.out.println("KEBAB_CASE: "+ json);
	}

}
