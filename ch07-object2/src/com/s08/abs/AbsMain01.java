package com.s08.abs;

//�߻�Ŭ����
//�̿ϼ��� �������� �ܵ����� ��ü ������ �Ұ����ϰ� �Ϲ������� �ڽ�Ŭ������ ��ӵǾ� ���

abstract class A {

	private int x;

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

}

class B extends A {
	int b = 200;
}

public class AbsMain01 {
	public static void main(String[] args) {
		// �߻�Ŭ������ ��ü ���� �Ұ���
		// A ap = new A();

		B bp = new B();

		bp.setX(100);
		System.out.println(bp.getX());
	}

}
