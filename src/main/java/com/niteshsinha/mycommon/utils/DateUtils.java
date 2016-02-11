package com.niteshsinha.mycommon.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Timestamp convertStringToTimestamp(String strDate) {
		String dt = "dd/MM/yyyy";
		return convertStringToTimestamp(strDate,dt);
	}
	
	public static Timestamp convertStringToTimestamp(String str_date, String dateFormat) {
	    try {
	      DateFormat formatter;
	      formatter = new SimpleDateFormat(dateFormat);
	       // you can change format of date
	      Date date = formatter.parse(str_date);
	      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

	      return timeStampDate;
	    } catch (ParseException e) {
	      System.out.println("Exception :" + e);
	      return null;
	    }
	  }
}
