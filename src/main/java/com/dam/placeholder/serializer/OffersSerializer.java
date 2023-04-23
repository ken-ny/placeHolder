package com.dam.placeholder.serializer;

import java.io.IOException;
import java.util.List;

import com.dam.placeholder.response.OffersResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class OffersSerializer extends JsonSerializer<List<OffersResponse>> {

	@Override
	public void serialize(List<OffersResponse> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartArray();
		for (OffersResponse model : value) {
			gen.writeStartObject();
			gen.writeObjectField("offer", model);
			gen.writeEndObject();
		}
		gen.writeEndArray();

	}

}
