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
	 * Convierte la fecha que se le pase a un objeto Date
	 * 
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

	/**
	 * Convierte el objeto date que se le pase un string con formato dd-MM-yyyy
	 * 
	 * @param date
	 * @return
	 */
	private static String convertDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}

	/**
	 * Devuelve una lista con los precios de venta de los objetos Sales que se le
	 * pasen
	 * 
	 * @param sales
	 * @return
	 */
	public List<Double> extractSalesPrices(List<Sales> sales) {
		return sales.stream().map(Sales::getSalePrice).collect(Collectors.toList());
	}

	/**
	 * Devuelve una lista de string de las fechas de cada uno de los objetos sales
	 * que se le informen formateadas dd-MM-yyyy
	 * 
	 * @param sales
	 * @return
	 */
	public List<String> extractFormatedDates(List<Sales> sales) {
		return sales.stream().map(Sales::getSaleDate).map(s -> Utils.convertDateToString(s))
				.collect(Collectors.toList());
	}

}
