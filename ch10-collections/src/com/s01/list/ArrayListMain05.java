package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain05 {
	public static void main(String[] args) {
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		a1.add(10);
		a1.add(20);
		a1.add(15);
		a1.add(16);
		System.out.println(a1);
		System.out.println("----------------");
		// 짝수인덱스 삭제
		// 인덱스0부터 마지막 인덱스까지 반복하면서 조건에 따라 데이털르 삭제할 경우 인덱스 변동이
		// 일어나기 때문에 체크하지 못하고 지나치는 데이터가 발생할 수 있다
		for (int i = 0; i < a1.size(); i++) {
			if (a1.get(i) % 2 == 0) {
				a1.remove(i);
			}

		}
		System.out.println(a1);
		System.out.println("----------------");
		// 여러 데이터를 삭제할 때는 주의해야한다.
		// 마지막 인덱스에서 인덱스0으로 반복문을 수행하면서 특정 조건일 때 데이터 삭제
		for (int i = a1.size() - 1; i >= 0; i--) {
			if (a1.get(i) % 2 == 0) {
				a1.remove(i);
			}
		}
		System.out.println(a1);
	}

}
