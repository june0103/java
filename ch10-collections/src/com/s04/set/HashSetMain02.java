package com.s04.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class HashSetMain02 {
	public static void main(String[] args) {
		// �ζ����α׷������
		// 1~45 �������� �ߺ����� ���� 6���� ���� HashSet�� �����ϰ� ����ϱ�
		Random rd = new Random();
		HashSet<Integer> hs = new HashSet<Integer>();
		while (hs.size() < 6) {

			int a = rd.nextInt(45) + 1;
			// System.out.println(a);
			hs.add(a);
		}

		System.out.println(hs);

		// ����
		List<Integer> list = new ArrayList<Integer>(hs);

		Collections.sort(list);
		for (int num : list) {
			System.out.print(num + "\t");
		}

	}
}
