package com.s01.stringex;

public class StringMain01 {
	public static void main(String[] args) {

		// 암시적으로 객체생성. 암시적으로 String객체를 생성하면 같은 문자열일때 같은 객체 공유
		String str1 = "abc";
		String str2 = "abc";

		if (str1 == str2) {
			System.out.println("같은객체");
		} else {
			System.out.println("다른객체");
		}
	}

}
