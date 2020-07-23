package com.samcancode.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samcancode.msscbeerservice.service.BeerService;
import com.samcancode.msscbeerservice.web.model.BeerDto;
import com.samcancode.msscbeerservice.web.model.BeerPagedList;
import com.samcancode.msscbeerservice.web.model.BeerStyleEnum;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE   = 25;
	
	private final BeerService beerSvc;
	
	public BeerController(BeerService beerSvc) {
		this.beerSvc = beerSvc;
	}
	
	@GetMapping(produces={"application/json"})
	public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value="pageNumber",required=false) Integer pageNumber,
												   @RequestParam(value="pageSize",required=false)   Integer pageSize,
												   @RequestParam(value="beerName",required=false)   String  beerName,
												   @RequestParam(value="beerStyle",required=false)  BeerStyleEnum beerStyle,
												   @RequestParam(value="showInventoryOnHand",required=false) Boolean showInventoryOnHand) {
		if(pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		
		if(pageSize == null || pageSize < 3) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		if (showInventoryOnHand == null) {
			showInventoryOnHand = false;
		}
		
		BeerPagedList beerList = beerSvc.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
		
		return new ResponseEntity<>(beerList, HttpStatus.OK);
	}
	
	//-------------------------------------------------------------------------------
	
	@GetMapping("/first")
	public ResponseEntity<BeerDto> getFirstBeer() {
		return new ResponseEntity<>(beerSvc.findFirstBeer(), HttpStatus.OK);
	}
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(
			 @PathVariable("beerId") UUID beerId,
			 @RequestParam(value="showInventoryOnHand",required=false) Boolean showInventoryOnHand) {
		
		if(showInventoryOnHand == null) { showInventoryOnHand = false; }
		
		return new ResponseEntity<>(beerSvc.findBeerById(beerId,showInventoryOnHand), HttpStatus.OK);
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
