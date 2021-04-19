package com.s04.set;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetMain {
	public static void main(String[] args) {
		String array[] = { "Java", "JSP", "Java", "Android" };

		HashSet<String> hs = new HashSet<String>();
		for (String a : array) {
			hs.add(a); // 객체 저장, 중복 불허
		}

		// Hashset에 저장된 요소
		System.out.println(hs);

		Iterator<String> ir = hs.iterator();
		// 저장된 요소가 있는지 검증
		while (ir.hasNext()) {
			System.out.println(ir.next());
		}
		System.out.println("--------------------");
		//확장 for문을 이용한 데이터 출력
		for (String s : hs) {
			System.out.println(s);
		}
	}

}
