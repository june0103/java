package com.s01.basic;

public class MethodMain02 {

	// ���� ���� ��� : ���� ���� ȣ��
	// �⺻ �ڷ����� ���� ���ڷ� �����ϴ� ���, ���� �����Ͽ� ����

	public int increase(int n) {
		++n;
		return n;
	}

	public static void main(String[] args) {

		int var1 = 100;

		MethodMain02 m = new MethodMain02();
		int var2 = m.increase(var1);
		System.out.println("var1 = " + var1 + var2);
	}

}
