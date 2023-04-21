package com.dam.placeholder.serializer;

import java.io.IOException;
import java.util.List;

import com.dam.placeholder.response.SalesResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SalesSerializer extends JsonSerializer<List<SalesResponse>> {

	@Override
	public void serialize(List<SalesResponse> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartArray();
		for (SalesResponse model : value) {
			gen.writeStartObject();
			gen.writeObjectField("sale", model);
			gen.writeEndObject();
		}
		gen.writeEndArray();

	}
}
