package com.s01.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exception05 {

	// throws예약어의 사용
	// 메서드에 throws 예외클래스를 명시하면 메서드내에 try~catch블럭을 생략할 수 있고
	// 예외가 발생하면 예외를 보관하고 메서들르 호출한 곳에 try~catch블럭이 있으면 try~catch에 예외를 양도함

	public void printData() throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("단입력:");
		int dan = Integer.parseInt(br.readLine());
		System.out.println(dan + "단");
		System.out.println("================");
		for (int i = 1; i <= 9; i++) {

			System.out.println(dan + "*" + i + "=" + dan * i);
		}
	}

	public static void main(String[] args) {

		Exception05 em = new Exception05();
		try {
			em.printData();
		} catch (Exception e) {
			System.out.println("예외발생");
		}

	}
}
