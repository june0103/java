package com.s08.abs;
//�߻�Ŭ������ Ŭ������ abstract����ϸ� �߻�Ŭ������ �ǰ� �Ϲ������� �߻�޼��带 �ϳ� �̻� ���´�.

abstract class A2 {
	// �߻�޼���. �߰�ȣ�θ� �����ʰ� �����ݷ����� ���´�
	public abstract void make();

	// �Ϲݸ޼���
	public void play() {
		System.out.println("A�� play");
	}

}

class B2 extends A2 {

	// �θ�Ŭ������ �߻�޼���� �ݵ�� ����
	@Override
	public void make() {
		System.out.println("B2�� make");

	}

}

public class AbsMain02 {
	public static void main(String[] args) {
		B2 bp = new B2();
		bp.make();
		bp.play();

	}
}
