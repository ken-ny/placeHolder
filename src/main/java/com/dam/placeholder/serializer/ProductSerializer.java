package com.dam.placeholder.serializer;

import java.io.IOException;

import com.dam.placeholder.response.ProductResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProductSerializer extends JsonSerializer<ProductResponse> {

	@Override
	public void serialize(ProductResponse value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartArray();
		gen.writeStartObject();
		gen.writeObjectField("product", value);
		gen.writeEndObject();
		gen.writeEndArray();
	}

}
