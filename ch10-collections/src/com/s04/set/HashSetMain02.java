package com.s04.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class HashSetMain02 {
	public static void main(String[] args) {
		// 로또프로그램만들기
		// 1~45 범위에서 중복되지 않은 6개의 수를 HashSet에 저장하고 출력하기
		Random rd = new Random();
		HashSet<Integer> hs = new HashSet<Integer>();
		while (hs.size() < 6) {

			int a = rd.nextInt(45) + 1;
			// System.out.println(a);
			hs.add(a);
		}

		System.out.println(hs);

		// 정렬
		List<Integer> list = new ArrayList<Integer>(hs);

		Collections.sort(list);
		for (int num : list) {
			System.out.print(num + "\t");
		}

	}
}
