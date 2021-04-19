package com.s01.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrayListMain07 {
	public static void main(String[] args) {
		// 로또 1~45 중복되지않은 6개의 수 ArrayList에 저장후 출력

		Random rd = new Random();
		List<Integer> list = new ArrayList<Integer>();

		while (list.size() < 6) {
			int a = rd.nextInt(45) + 1;
			System.out.println(a);
			if (!list.contains(a)) {
				list.add(a);
			}

		}
		System.out.println(list);
		// 정렬
		Collections.sort(list);
		System.out.println(list);
	}

}
