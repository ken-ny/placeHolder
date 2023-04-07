package com.dam.placeholder.response.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.ProductExpansion;
import com.dam.placeholder.response.ExpansionResponse;

public class ResponseUtils {

	public static List<ExpansionResponse> mapperExpansionToResponse(List<Expansion> listExpansions) {

		List<ExpansionResponse> exResponse = new ArrayList<>();
		listExpansions.stream().filter(Objects::nonNull).forEach(ex -> exResponse.add(new ExpansionResponse(ex)));

		return exResponse;

	}

	public static List<ExpansionResponse> mapperProductExpansionToResponse(List<ProductExpansion> listExpansions) {

		List<ExpansionResponse> exResponse = new ArrayList<>();
		listExpansions.stream().filter(Objects::nonNull)
				.forEach(ex -> exResponse.add(new ExpansionResponse(ex.getExpansionId())));

		return exResponse;

	}

}
