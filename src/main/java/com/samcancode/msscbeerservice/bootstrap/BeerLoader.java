package com.samcancode.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.samcancode.msscbeerservice.domain.Beer;
import com.samcancode.msscbeerservice.repository.BeerRepository;

//@Component //uncomment to enable; has been replaced with data.sql loading for easier testing
public class BeerLoader implements CommandLineRunner {
	public static final String BEER_1_UPC = "0612334200036";
	public static final String BEER_2_UPC = "0612334300019";
	public static final String BEER_3_UPC = "0083783375213";
	
	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if(beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Mango Bobs")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.minOnHand(12)
					.upc(BEER_1_UPC)
					.price(new BigDecimal("12.95"))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle("PALE_ALE")
					.quantityToBrew(200)
					.minOnHand(12)
					.upc(BEER_2_UPC)
					.price(new BigDecimal("11.95"))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Hammers on the Bar")
					.beerStyle("PALE_ALE")
					.quantityToBrew(230)
					.minOnHand(11)
					.upc(BEER_3_UPC)
					.price(new BigDecimal("3.95"))
					.build());
		}
	}
	
	
}
