package com.s07.finalex;

class A {
	// ����ʵ�
	// ���
	final int NUM = 10;
	// static ���
	public static final int NUMBER = 20;

}

public class FinalMain01 {

	public static void main(String[] args) {
		A ap = new A();
		System.out.println(ap.NUM);
		// ������� ���� �Ұ���
		// ap.NUM = 100;

		// static ���
		System.out.println(A.NUMBER);

		// ���� ���
		final int NO = 30;

		System.out.println(NO);
	}

}
