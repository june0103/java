package com.s01.exception;

public class ExceptionMain01 {
	public static void main(String[] args) {
		int[] array = { 10, 20, 30 };
		// 일부러 없는 인덱스(3)을 호출해서 예외를 발생시킴
		// 예외가 발생하면 예외가 발생한 지점에서 프로그램이 강제 종료

		for (int i = 0; i <= array.length; i++) {
			System.out.println("array[" + i + "]:" + array[i]);
		}

		System.out.println("프로그램 끝");
	}

}
