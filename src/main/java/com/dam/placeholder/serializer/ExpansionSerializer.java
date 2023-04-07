package com.dam.placeholder.serializer;

import java.io.IOException;
import java.util.List;

import com.dam.placeholder.response.ExpansionResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ExpansionSerializer extends JsonSerializer<List<ExpansionResponse>> {

	@Override
	public void serialize(List<ExpansionResponse> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartArray();
		for (ExpansionResponse model : value) {
			gen.writeStartObject();
			gen.writeObjectField("expansion", model);
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}

}
