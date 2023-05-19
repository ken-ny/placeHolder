package com.dam.placeholder.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.dam.placeholder.entity.Sales;

public class Utils {

	/**
	 * @return
	 * @throws ParseException
	 */
	public Date convertDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}

	/**
	 * @param sales
	 * @return
	 */
	public List<Double> extractSalesPrices(List<Sales> sales) {
		return sales.stream().map(Sales::getSalePrice).collect(Collectors.toList());
	}

	/**
	 * @param sales
	 * @return
	 */
	public List<String> extractFormatedDates(List<Sales> sales) {
		return sales.stream().map(Sales::getSaleDate).map(s -> Utils.convertDateToString(s))
				.collect(Collectors.toList());
	}

}
