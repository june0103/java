package com.s04.date;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarMain02 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();

		System.out.println("희망 연도와 월을 입력(ex 년:2021, 월:1");
		System.out.print("연도 : ");
		int year = input.nextInt();
		System.out.print("월 : ");
		int month = input.nextInt();

		System.out.println("       [" + year + "년 " + month + "월]");
		System.out.println("---------------------------");
		System.out.println(" 일  월  화  수 목  금 토");

		// 희망하는 연도, 월, 일 셋팅. 월의 범위는 0~11이기 때문에 입력월-1
		// 일은 달력이 1일부터 시작하기 때문에 1일로 셋팅
		// (년, 월, 일)
		cal.set(year, month - 1, 1);
		// 1일의 요일 구하기(1 - 일요일 ~ 7 - 토요일)
		int week = cal.get(Calendar.DAY_OF_WEEK);
		// 월의 마지막 날을 구하기
		int lastOfDate = cal.getActualMaximum(Calendar.DATE);

		// 달력 월의 첫주 시작하는날까지 공백 입력
		for (int i = 1; i < week; i++) {
			System.out.printf("%3s", " ");
		}
		// 1일~월의 마지-막 날짜까지 반복문을 이용해서 표시
		for (int i = 1; i <= lastOfDate; i++) {
			System.out.printf("%3d", i);
			if (week % 7 == 0) {
				System.out.println();
			}
			week++;

		}

		input.close();
	}
}
