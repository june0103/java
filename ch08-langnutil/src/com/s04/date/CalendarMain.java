package com.s04.date;

import java.util.Calendar;

public class CalendarMain {
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		System.out.println(today);

		System.out.print("오늘은 ");
		int year = today.get(Calendar.YEAR); // 연도
		int month = today.get(Calendar.MONTH) + 1; // 월 0~11
		int date = today.get(Calendar.DATE); // 일

		System.out.print(year + "년" + month + "월" + date + "일 ");

		int day = today.get(Calendar.DAY_OF_WEEK);
		String nday = "";
		switch (day) {
		case 1:
			nday = "일";
			break;
		case 2:
			nday = "월";
			break;
		case 3:
			nday = "화";
			break;
		case 4:
			nday = "수";
			break;
		case 5:
			nday = "목";
			break;
		case 6:
			nday = "금";
			break;
		case 7:
			nday = "토";
			break;
		}
		System.out.print(nday + "요일 ");

		int amPm = today.get(Calendar.AM_PM); // 오전0, 오후1
		String str = amPm == Calendar.AM ? "오전" : "오후";
		System.out.print(str + " ");

		int hour = today.get(Calendar.HOUR+1); // 시, HOUR_OF_DAY(24시 표기)
		int hour24 = today.get(Calendar.HOUR_OF_DAY); //24시표기
		int min = today.get(Calendar.MINUTE); // 분
		int sec = today.get(Calendar.SECOND); // 초
		System.out.println(hour24 + ":" + min + ":" + sec);
	}

}
