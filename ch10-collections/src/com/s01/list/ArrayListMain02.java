package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain02 {
	public static void main(String[] args) {

		// 리스트는 저장할때 .add 출력할때 .get

		ArrayList a1 = new ArrayList();
		a1.add("강호동"); // String->object
		a1.add("박명수"); // String->object
		a1.add("유재석"); // String->object
		a1.add("김구라"); // String->object

		// 반복문을 이용해서 저장된 요소 출력
		for (int i = 0; i < a1.size(); i++) {
			String name = (String) a1.get(i); // object -> String 다운캐스팅 부모에서 자식으로
			System.out.println(name);
		}
	}

}
