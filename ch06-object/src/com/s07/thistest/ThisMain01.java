package com.s07.thistest;

public class ThisMain01 {
	// this�� ��ü ���ο��� ��ü�� ��Ī�� �� ����� �� �ְ� ������ �������� ������ ��.
	// ��ü�� ������(�ʵ�,�޼���)�� ȣ���� �� ���

	public ThisMain01() {
		System.out.println(this);// ��ü�� �ּ�
	}

	public static void main(String[] args) {
		ThisMain01 tm = new ThisMain01();
		System.out.println(tm);
	}

}
