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
		// ¦���ε��� ����
		// �ε���0���� ������ �ε������� �ݺ��ϸ鼭 ���ǿ� ���� �����и� ������ ��� �ε��� ������
		// �Ͼ�� ������ üũ���� ���ϰ� ����ġ�� �����Ͱ� �߻��� �� �ִ�
		for (int i = 0; i < a1.size(); i++) {
			if (a1.get(i) % 2 == 0) {
				a1.remove(i);
			}

		}
		System.out.println(a1);
		System.out.println("----------------");
		// ���� �����͸� ������ ���� �����ؾ��Ѵ�.
		// ������ �ε������� �ε���0���� �ݺ����� �����ϸ鼭 Ư�� ������ �� ������ ����
		for (int i = a1.size() - 1; i >= 0; i--) {
			if (a1.get(i) % 2 == 0) {
				a1.remove(i);
			}
		}
		System.out.println(a1);
	}

}
