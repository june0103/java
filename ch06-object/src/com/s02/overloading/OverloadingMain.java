package com.s02.overloading;

public class OverloadingMain {
	// Method Overloading(�ߺ�����)�̶�� ���� �ϳ��� Ŭ���� ������ ���� �̸��� ������ �޼��尡 ������ ���ǵǴ� ���� ���Ѵ�.
	// ������ Ÿ�� �Ǵ� ����, ��ġ�� ������ �ٸ���� �ٸ� �޼���� �ν��Ѵ�.
	// �޼����� ����� �ٸ��� �̸��� �ٸ��� �����ؾ��Ѵ�

	public void print(int n) {
		System.out.println("���� n = " + n);
	}

	public void print(double n) {
		System.out.println("�Ǽ� n = " + n);
	}

	public void print(double n, long a) {
		System.out.println("�Ǽ� n = " + n + ", ���� a = " + a);
	}

	public void print(long a, double n) {
		System.out.println("���� a = " + a + ", �Ǽ� n = " + n);
	}

	public static void main(String[] args) {
		OverloadingMain om = new OverloadingMain();

		om.print(20);
		om.print(5.6);
		om.print(6.3, 1234l);
		om.print(1234l, 3.7);
	}

}
