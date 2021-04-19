package com.s03.wrapper;

public class WrapperMain {
	public static void main(String[] args) {
		char c = 'A';
		// 객체 생성
		// 기본자료형 데이터 -> 참조자료형 데이터
		Character wrap_c = new Character(c);
		// 참조자료형 데이터 -> 기본자료형 데이터
		System.out.println(wrap_c.charValue());

		int d = 12;
		// 객체 생성
		// 기본자료형 데이터 -> 참조자료형 데이터
		Integer wrap_d = new Integer(d);
		// 참조자료형 데이터 -> 기본자료형 데이터
		System.out.println(wrap_d.intValue());
	}

}
