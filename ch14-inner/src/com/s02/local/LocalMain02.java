package com.s02.local;

public class LocalMain02 {

	public void innerTest() {
		int a = 100; // ��������

		// ���� ����Ŭ����
		class Inner {
			public void getData() {
				// JDK8.0���ʹ� ����Ŭ������ ���Ե� �޽����� ���������� ȣ���ϸ� �ڵ����� ����� ��ȯ�ȴ�

				// a = 200;
				System.out.println("a : " + a);
			}
		}
		// ���� ����Ŭ���� ��ü ����
		Inner i = new Inner();
		i.getData();
	}

	public static void main(String[] args) {
		LocalMain02 m = new LocalMain02();
		m.innerTest();
	}
}
