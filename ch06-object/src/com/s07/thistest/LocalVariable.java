package com.s07.thistest;

public class LocalVariable {
	// ������� : Ŭ���� �� ���������ǵ� ���� , Ŭ���� ��(Ŭ������ { })
	int b = 200;

	public void make() {
		// �������� : �޼���, ������, ��� �� ������ ������ ����, �ش� ���� ����Ǹ� ������ �Ҹ�

		int a = 100;
		System.out.println(a); // ��������
		System.out.println(b); // �������

	}

	public void fun() {
		// fun�޼��� �ȿ� a���������ǵ��� �ʾƼ� ȣ�� �Ұ���
		System.out.println(b); // ��������� ȣ�Ⱑ��
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) { // ���, �ݺ��� �� ���� ��������
			System.out.println(i);
		}
		// ���, �ݺ��� ���� �������� �������� i�� �Ҹ�Ǿ� ȣ���� �Ұ���

	}
}
