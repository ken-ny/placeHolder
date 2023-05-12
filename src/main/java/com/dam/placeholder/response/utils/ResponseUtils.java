package com.dam.placeholder.response.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.response.Error;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.SaleDetailsResponse;

public class ResponseUtils {

	public static List<ExpansionResponse> mapperExpansionToResponse(List<Expansion> listExpansions) {
		if (CollectionUtils.isEmpty(listExpansions)) {
			return Collections.emptyList();
		}

		List<ExpansionResponse> exResponse = new ArrayList<>();
		listExpansions.stream().filter(Objects::nonNull).forEach(ex -> exResponse.add(new ExpansionResponse(ex)));

		return exResponse;

	}

	public static Date convertStringToDate(String release_date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse(release_date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static List<SaleDetailsResponse> mapperDetailsToResponse(List<SaleDetails> listDetails) {
		if (CollectionUtils.isEmpty(listDetails)) {
			return Collections.emptyList();
		}

		List<SaleDetailsResponse> response = new ArrayList<>();

		listDetails.stream().filter(Objects::nonNull).forEach(ex -> response.add(new SaleDetailsResponse(ex)));

		return response;
	}

	public static ExpansionResponse mapperExpansionToResponse(Expansion expansion) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Genera un objeto error con el codigo, titulo y detalle que se le pasen
	 * 
	 * @return
	 */
	public static Error generateError(String code, String title, String detail) {
		return new Error(code, title, detail);
	}

}