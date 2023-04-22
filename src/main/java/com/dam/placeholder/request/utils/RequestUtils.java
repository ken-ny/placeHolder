package com.dam.placeholder.request.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.request.SaleDetailsRequest;

public class RequestUtils {
	public static List<SaleDetails> mapperSaleDetailsRequestToObject(List<SaleDetailsRequest> listDetails) {

		List<SaleDetails> exResponse = new ArrayList<>();
		listDetails.stream().filter(Objects::nonNull).forEach(ex -> exResponse.add(new SaleDetails(ex)));

		return exResponse;

	}

}
