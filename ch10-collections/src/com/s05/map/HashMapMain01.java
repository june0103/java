package com.s05.map;

import java.util.HashMap;

public class HashMapMain01 {
	public static void main(String[] args) {
		// <key, value>Ÿ������
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		// HashMap�� key�� value ����
		map.put("���", 95);
		map.put("����Ź", 100);
		map.put("���»���", 85);
		map.put("���", 93);
		map.put("����ȭ", 70);
		// key�� �ߺ��Ǹ� �������� �Է��� ���� ����
		map.put("����Ź", 0);
		// value���� null�� �������� ��õ�����ʴ´�. value���� ���� �� �����ڷ������δ� �������� �ʴ´�
		map.put("��ȣ��", null);
		// key�� null�� �������� ��õ�����ʴ´�
		map.put(null, 100);
		// HashMap�� ����� key�� value ���
		System.out.println(map);
		System.out.println(map.get("���"));

	}
}
