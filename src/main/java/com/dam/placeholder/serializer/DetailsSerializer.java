package com.dam.placeholder.serializer;

import java.io.IOException;
import java.util.List;

import com.dam.placeholder.response.SaleDetailsResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DetailsSerializer extends JsonSerializer<List<SaleDetailsResponse>> {

	@Override
	public void serialize(List<SaleDetailsResponse> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartArray();
		for (SaleDetailsResponse model : value) {
			gen.writeStartObject();
			gen.writeObjectField("detail", model);
			gen.writeEndObject();
		}
		gen.writeEndArray();

	}

}
