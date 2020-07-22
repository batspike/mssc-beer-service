package com.samcancode.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samcancode.msscbeerservice.service.BeerService;
import com.samcancode.msscbeerservice.web.model.BeerDto;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	private final BeerService beerSvc;
	
	public BeerController(BeerService beerSvc) {
		this.beerSvc = beerSvc;
	}
	
	@GetMapping("/first")
	public ResponseEntity<BeerDto> getFirstBeer() {
		return new ResponseEntity<>(beerSvc.findFirstBeer(), HttpStatus.OK);
	}
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerSvc.findBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
		BeerDto savedBeerDto = beerSvc.saveBeer(beerDto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedBeerDto.getId().toString());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId,
												  @Valid @RequestBody BeerDto beerDto) {
		BeerDto savedBeer = beerSvc.updateBeerById(beerId,beerDto);
		if(savedBeer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{beerId}")
	public ResponseEntity<BeerDto> deleteBeerById(@PathVariable("beerId") UUID beerId) {
		beerSvc.deleteBeerById(beerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
