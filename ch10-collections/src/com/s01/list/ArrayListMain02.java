package com.s01.list;

import java.util.ArrayList;

public class ArrayListMain02 {
	public static void main(String[] args) {

		// ����Ʈ�� �����Ҷ� .add ����Ҷ� .get

		ArrayList a1 = new ArrayList();
		a1.add("��ȣ��"); // String->object
		a1.add("�ڸ��"); // String->object
		a1.add("���缮"); // String->object
		a1.add("�豸��"); // String->object

		// �ݺ����� �̿��ؼ� ����� ��� ���
		for (int i = 0; i < a1.size(); i++) {
			String name = (String) a1.get(i); // object -> String �ٿ�ĳ���� �θ𿡼� �ڽ�����
			System.out.println(name);
		}
	}

}
