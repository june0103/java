package com.s01.list;

import java.util.Vector;

public class VectorMain {
	public static void main(String[] args) {
		Vector<Double> v = new Vector<Double>();

		v.add(100.3);
		v.add(3.14);
		v.add(1000.); // =1000.0

		// 확장 for문을 이용한 요소의 출력
		for (Double nDouble : v) {
			System.out.println(nDouble);
		}

		// 자원검색
		double search = 1000.0; // 검색할 요소
		int index = v.indexOf(search);

		if (index != -1) {
			System.out.println("검색 요소 " + search + "의 위치 : " + index);
		} else {
			System.out.println("검새 요소 " + search + "이 없습니다.");
		}

		// 자원삭제
		double del = 3.14;
		if (v.contains(del)) {
			v.remove(del);
			System.out.println(del + " 삭제 완료!");
		}
		System.out.println(v);
	}

}
