package com.s01.list;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListMain06 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("머루"); // 0
		list.add("사과"); // 1
		list.add("앵두"); // 2
		list.add("자두"); // 3
		list.add("사과"); // 4
		// 인덱스 탐색
		int index1 = list.indexOf("사과");
		System.out.println("첫번재 사과 : " + index1);

		int index2 = list.lastIndexOf("사과");
		System.out.println("마지막 사과 : " + index2);

		int index3 = list.indexOf("망고");
		System.out.println("망고 : " + index3);

		// 정렬(사전에 정의된 순서)
		Collections.sort(list);
		System.out.println(list);

		// 역순으로 정렬(사전에 정의된 역순)
		Collections.reverse(list);
		System.out.println(list);

		/*
		 * List --> ArrayList 스레드 동기화를 지원x 속도가빠름 List --> Vector 스레드 동기화를 지원o 속도가 느림
		 */
	}

}
