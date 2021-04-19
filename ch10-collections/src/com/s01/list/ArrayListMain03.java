package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain03 {
	public static void main(String[] args) {

		// 제네릭 표현 : 객체를 생성할 때 객체에 저장할 수 있는 요소의 타입을 지정
		// String으로 지정해서 저장되는 객체의 자료형은 String만 인정
		// 다른 타입의 객체를 저장하면 오류 발생
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("강호동");
		a1.add("김구라");
		// a1.add(1000);
		a1.add("박명수");

		for (int i = 0; i < a1.size(); i++) {

			System.out.println(a1.get(i));
		}
	}
}
