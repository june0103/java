package com.s01.stringex;

public class StringMain02 {
	public static void main(String[] args) {
		String s1 = "Kwon Sun Ae";
		// 012345678910 , 문자열의 길이 11

		int index = s1.indexOf('n'); // 첫번째 n의 위치
		System.out.println(index);

		index = s1.indexOf("Sun"); // Sum의 위치. S의 인덱스 반환
		System.out.println(index);

		index = s1.lastIndexOf('n'); // 마지막 n의 위치
		System.out.println(index);

		char c = s1.charAt(index); // 문자 추출
		System.out.println(c);

		index = s1.indexOf('S');
		// 지정한 인덱스부터 마지막 인덱스까지 문자열 추출
		String string = s1.substring(index);
		System.out.println(string);

		// 시작인덱스부터 끝 인덱스 전까지 문자열 추출
		string = s1.substring(index, index + 3); // 5,8
		System.out.println(string);

		int length = s1.length();
		System.out.println(length);

		String array[] = s1.split(""); // ""빈분자열 - 문자열객체를 생성했지만 데이터가 없다라는 뜻
		for (int i = 0; i < array.length; i++) {
			System.out.println("array[" + i + "] : " + array[i]);
		}
	}

}
