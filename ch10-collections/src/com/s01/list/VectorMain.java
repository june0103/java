package com.s01.list;

import java.util.Vector;

public class VectorMain {
	public static void main(String[] args) {
		Vector<Double> v = new Vector<Double>();

		v.add(100.3);
		v.add(3.14);
		v.add(1000.); // =1000.0

		// Ȯ�� for���� �̿��� ����� ���
		for (Double nDouble : v) {
			System.out.println(nDouble);
		}

		// �ڿ��˻�
		double search = 1000.0; // �˻��� ���
		int index = v.indexOf(search);

		if (index != -1) {
			System.out.println("�˻� ��� " + search + "�� ��ġ : " + index);
		} else {
			System.out.println("�˻� ��� " + search + "�� �����ϴ�.");
		}

		// �ڿ�����
		double del = 3.14;
		if (v.contains(del)) {
			v.remove(del);
			System.out.println(del + " ���� �Ϸ�!");
		}
		System.out.println(v);
	}

}
