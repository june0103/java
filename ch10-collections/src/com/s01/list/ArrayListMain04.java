package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain04 {
	public static void main(String[] args) {
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("����");
		a1.add("�λ�");
		a1.add("����");
		a1.add("�뱸");
		a1.add("��õ");

		for (int i = 0; i < a1.size(); i++) {
			System.out.println(i + ":" + a1.get(i));
		}

		System.out.println("=============");

		// ����Ʈ ��� ����
		// a1.remove(2); // �ε���
		a1.remove("�λ�"); // ���
		for (int i = 0; i < a1.size(); i++) {
			System.out.println(i + ":" + a1.get(i));
		}
	}
}
