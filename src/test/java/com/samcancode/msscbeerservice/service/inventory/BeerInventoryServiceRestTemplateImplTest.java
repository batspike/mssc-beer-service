package com.samcancode.msscbeerservice.service.inventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samcancode.msscbeerservice.bootstrap.BeerLoader;

@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {
	
	@Autowired
	BeerInventoryService beerInventoryService;
	
	@BeforeEach
	void setUp() {
		
	}

	@Test
	void getOnhandInventoryTest() {
		Integer qoh = beerInventoryService.getOnhandInventory(UUID.fromString(BeerLoader.BEER_1_UUID));
		System.out.println("********"+ qoh);
	}

}
