package com.s07.finalex;

class A {
	// 멤버필드
	// 상수
	final int NUM = 10;
	// static 상수
	public static final int NUMBER = 20;

}

public class FinalMain01 {

	public static void main(String[] args) {
		A ap = new A();
		System.out.println(ap.NUM);
		// 상수값은 변경 불가능
		// ap.NUM = 100;

		// static 상수
		System.out.println(A.NUMBER);

		// 지역 상수
		final int NO = 30;

		System.out.println(NO);
	}

}
