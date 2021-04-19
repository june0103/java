package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain04 {
	public static void main(String[] args) {
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("서울");
		a1.add("부산");
		a1.add("광주");
		a1.add("대구");
		a1.add("인천");

		for (int i = 0; i < a1.size(); i++) {
			System.out.println(i + ":" + a1.get(i));
		}

		System.out.println("=============");

		// 리스트 요소 삭제
		// a1.remove(2); // 인덱스
		a1.remove("부산"); // 요소
		for (int i = 0; i < a1.size(); i++) {
			System.out.println(i + ":" + a1.get(i));
		}
	}
}
