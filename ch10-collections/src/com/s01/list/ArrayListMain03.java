package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain03 {
	public static void main(String[] args) {

		// ���׸� ǥ�� : ��ü�� ������ �� ��ü�� ������ �� �ִ� ����� Ÿ���� ����
		// String���� �����ؼ� ����Ǵ� ��ü�� �ڷ����� String�� ����
		// �ٸ� Ÿ���� ��ü�� �����ϸ� ���� �߻�
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("��ȣ��");
		a1.add("�豸��");
		// a1.add(1000);
		a1.add("�ڸ��");

		for (int i = 0; i < a1.size(); i++) {

			System.out.println(a1.get(i));
		}
	}
}
