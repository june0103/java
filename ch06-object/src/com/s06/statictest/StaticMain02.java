package com.s06.statictest;

public class StaticMain02 {
	// �ν��Ͻ�����
	int a;
	// static ����
	static String s;

	public static void main(String[] args) {

		// static ������ ȣ���ϸ� �޸𸮿� �������
		s = "�ܿ�";
		// �ν��Ͻ� ������ ��ü ���� �� ȣ���ؾ� ��
		// a = 10;

		StaticMain02 sm = new StaticMain02();
		sm.a = 20;

	}

}
