package com.web.framework.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LocalDate {

	Date date;

	public LocalDate() {
	
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		date = new Date();
	}

	public Date now() {
		return this.date;
	}

	public Date add(int days) {
		long ltime = this.date.getTime() + (days * 24 * 60 * 60 * 1000);
		return new Date(ltime);
	}
	public Date getNormalValidity() {
		Calendar c = Calendar.getInstance();
        c.setTime(this.date);
        c.add(Calendar.YEAR, 10);
        return c.getTime();
	}

	 
}
