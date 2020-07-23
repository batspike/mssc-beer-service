package com.samcancode.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samcancode.msscbeerservice.bootstrap.BeerLoader;
import com.samcancode.msscbeerservice.web.model.BeerDto;
import com.samcancode.msscbeerservice.web.model.BeerStyleEnum;

@SpringBootTest
@AutoConfigureMockMvc
class BeerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void testGetFirstBeer() throws Exception {
		MvcResult r = mockMvc.perform(get("/api/v1/beer/first").accept(MediaType.APPLICATION_JSON)).andReturn();
		BeerDto beerDto = objectMapper.readValue(r.getResponse().getContentAsString(), BeerDto.class);
		
		mockMvc.perform(get("/api/v1/beer/"+ beerDto.getId(),false).accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
	}
	
	@Test
	void testGetBeerById() throws Exception {
		MvcResult r = mockMvc.perform(get("/api/v1/beer/first").accept(MediaType.APPLICATION_JSON)).andReturn();
		BeerDto beerDto = objectMapper.readValue(r.getResponse().getContentAsString(), BeerDto.class);
		
		mockMvc.perform(get("/api/v1/beer/"+ beerDto.getId(),false).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void testSaveNewBeer() throws Exception {

		BeerDto beerDto = BeerDto.builder()
				.upc(BeerLoader.BEER_1_UPC)
				.price(new BigDecimal("12.29"))
				.quantityOnHand(200)
				.beerName("Tiger Beer")
				.beerStyle(BeerStyleEnum.PALE_ALE)
				.build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
			   .andExpect(status().isCreated());
	}

	@Test
	void testUpdateBeerById() throws Exception {
		MvcResult r = mockMvc.perform(get("/api/v1/beer/first").accept(MediaType.APPLICATION_JSON)).andReturn();
		BeerDto beerDto = objectMapper.readValue(r.getResponse().getContentAsString(), BeerDto.class);
		UUID beerId = beerDto.getId();
		beerDto.setId(null);
		beerDto.setVersion(null);
		beerDto.setCreatedDate(null);
		beerDto.setLastModifiedDate(null);
		beerDto.setUpc("32120000212");
		beerDto.setPrice(new BigDecimal("13.99"));
		beerDto.setQuantityOnHand(300);
		beerDto.setBeerName("Best Beer");
		beerDto.setBeerStyle(BeerStyleEnum.PALE_ALE);

		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer/"+ beerId).contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
			   .andExpect(status().isNoContent());
	}

	@Test
	void testDeleteBeerById() throws Exception {
		MvcResult r = mockMvc.perform(get("/api/v1/beer/first").accept(MediaType.APPLICATION_JSON)).andReturn();
		BeerDto beerDto = objectMapper.readValue(r.getResponse().getContentAsString(), BeerDto.class);
			
		mockMvc.perform(delete("/api/v1/beer/"+ beerDto.getId()).accept(MediaType.APPLICATION_JSON))
		   .andExpect(status().isNoContent());
	}

}
