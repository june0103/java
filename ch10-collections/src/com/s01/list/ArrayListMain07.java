package com.s01.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrayListMain07 {
	public static void main(String[] args) {
		// �ζ� 1~45 �ߺ��������� 6���� �� ArrayList�� ������ ���

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
		// ����
		Collections.sort(list);
		System.out.println(list);
	}

}
