package com.s01.stringex;

public class StringMain03 {
	public static void main(String[] args) {
		String s1 = " aBa ";
		String s2 = "abc";
		int a = 100;
		String msg = null;

		// 소문자 -> 대문자
		msg = s1.toUpperCase();
		System.out.println(msg);

		// 대문자 -> 소문자
		msg = s1.toLowerCase();
		System.out.println(msg);

		// old문자 -> new문자로 교체
		msg = s1.replace("aB", "b");
		System.out.println(msg);

		// 앞뒤 공백 제거
		msg = s1.trim();
		System.out.println(msg);

		// 인자로 전달되는 문자열이 포함되어 있는지 검증
		boolean f = s1.contains("aB");
		System.out.println(f);

		// 인자로 전달되는 문자열로 시작하는 검증
		f = s2.startsWith("ab");
		System.out.println(f);

		// 인자로 전달되는 문자열로 끝나는지 검증
		f = s2.endsWith("bc");
		System.out.println(f);

		// int->string
		msg = String.valueOf(a);
		System.out.println(msg);

		msg = a + "";
		System.out.println(msg);
	}
}
