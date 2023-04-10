package com.dam.placeholder.serializer;

import java.io.IOException;

import com.dam.placeholder.response.GameResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GameSerializer extends JsonSerializer<GameResponse> {

	@Override
	public void serialize(GameResponse value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartArray();
		gen.writeStartObject();
		gen.writeObjectField("game", value);
		gen.writeEndObject();
		gen.writeEndArray();
	}

}
