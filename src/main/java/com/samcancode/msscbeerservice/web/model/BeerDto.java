package com.samcancode.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
	@JsonProperty("beerId") //overrides Json spring.jackson.property-naming-strategy config setting
	@Null
	private UUID id;
	
	@Null
	private Integer version;
	
	@Null
	//@JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING) //reformat date and translate to string in Json output
	@JsonSerialize(using=OffsetDateTimeSerializer.class) //alternative to the above
	@JsonDeserialize(using=OffsetDateTimeDeserializer.class)
	private OffsetDateTime createdDate;
	
	@Null
	private OffsetDateTime lastModifiedDate;
	
	@NotBlank
	private String beerName;
	
	@NotNull
	private BeerStyleEnum beerStyle;
	
	@Positive
	@NotNull
	private String upc;
	
	@Positive
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING) //translated to string in JSON output
	private BigDecimal price;
	
	private Integer quantityOnHand;
}
