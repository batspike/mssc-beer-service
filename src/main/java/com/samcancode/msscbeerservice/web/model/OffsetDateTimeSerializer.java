package com.samcancode.msscbeerservice.web.model;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

// example codes of a Jackson Json Serializer for OffsetDateTime type
public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

	@Override
	public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeObject(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
	
}
