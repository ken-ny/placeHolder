package com.dam.placeholder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
