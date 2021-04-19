package com.s03.wrapper;

public class WrapperMain02 {
	public static void main(String[] args) {

		// 기본자료형 데이터 -> 참조자료형 데이터 : auto boxing
		Integer obj1 = 10;
		Integer obj2 = 20;

		Integer result = obj1 + obj2;

		// 참조자료형 데이터 -> 기본자료형 데이터 : auto unboxing
		int result2 = obj1 + obj2;
		System.out.println(result);

	}

}
