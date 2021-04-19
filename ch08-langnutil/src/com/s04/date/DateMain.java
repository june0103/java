package com.s04.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMain {
	public static void main(String[] args) {
		
		Date now = new Date();
		
		DateFormat df = DateFormat.getInstance();
		String today = df.format(now);
		System.out.println(now.toLocaleString());
		
		df = DateFormat.getDateInstance();
		today = df.format(now);
		System.out.println(today);
		
		df= DateFormat.getTimeInstance();
		today = df.format(now);
		System.out.println(today);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy≥‚MMø˘dd¿œ (E) a hh:mm:ss");
		today = sf.format(now);
		System.out.println(today);
		
	}
}
