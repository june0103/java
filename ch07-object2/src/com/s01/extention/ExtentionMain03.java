package com.s01.extention;

//�θ�Ŭ����
class A {
	int x = 100;
	// ���� Ŭ������������ ȣ�� ����
	private int y = 200;
	
	public int getY() {
		return y;
	}

}

//�ڽ�Ŭ����
class B extends A {
	int z = 300;
}

public class ExtentionMain03 {
	public static void main(String[] args) {
		B b = new B();
		System.out.println(b.x);
		// private ��������� ���� Ŭ������������ ȣ���� �����ϴ�. �ڽ�Ŭ���������� x
		// System.out.println(b.y);
		System.out.println(b.getY());
		System.out.println(b.z);
	}

}
