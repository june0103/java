package com.s05.map;

import java.util.HashMap;

public class HashMapMain01 {
	public static void main(String[] args) {
		// <key, value>타입지정
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		// HashMap에 key와 value 저장
		map.put("김신", 95);
		map.put("지은탁", 100);
		map.put("저승사자", 85);
		map.put("써니", 93);
		map.put("유덕화", 70);
		// key가 중복되면 마지막에 입력한 값이 인정
		map.put("지은탁", 0);
		// value값에 null이 허용되지만 추천하지않는다. value값을 구할 때 참조자료형으로는 구해지지 않는다
		map.put("강호동", null);
		// key에 null이 허용되지만 추천하지않는다
		map.put(null, 100);
		// HashMap에 저장된 key와 value 목록
		System.out.println(map);
		System.out.println(map.get("김신"));

	}
}
