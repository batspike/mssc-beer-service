package com.samcancode.msscbeerservice.web.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

// example code of Jackson Json Deserializer customization
public class OffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {
	private static final long serialVersionUID = 1L;

	public OffsetDateTimeDeserializer() {
		super(OffsetDateTime.class);
	}

	@Override
	public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		LocalDate ld = LocalDate.parse(p.readValueAs(String.class), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		OffsetDateTime odt = OffsetDateTime.from(ld.atTime(OffsetTime.MIN)); //need to add offset time for OffsetDateTime type
		
		return odt;
	}
	
	
}
