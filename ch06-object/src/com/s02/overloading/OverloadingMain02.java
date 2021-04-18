package com.s02.overloading;

public class OverloadingMain02 {

	// Method Overloading (중복정의)
	public void getLength(int a) {
		String s = String.valueOf(a); // int -> String
		// 문자열의 길이
		// System.out.println(s.length());
		getLength(s);
	}

	public void getLength(float a) {
		String s = String.valueOf(a); // float -> String
		// 문자열의 길이
		// System.out.println(s.length());
		getLength(s);
	}

	public int getLength(String str) {

		System.out.println(str.length());
		return str.length();
	}

	public static void main(String[] args) {

		OverloadingMain02 ov = new OverloadingMain02();
		ov.getLength(10000); // 10000 -> "10000", 5
		ov.getLength(3.16f); // 3.14f -> "3.14" , 4
		ov.getLength("하이"); //

	}

}
