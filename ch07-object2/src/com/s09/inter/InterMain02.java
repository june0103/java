package com.s09.inter;

//�������̽� : ���ĸ� �ְ� ������ ����
interface A2 {
	// �߻�޼���
	public abstract void make(); // ����

	void play();
}

//�������̽��� Ŭ������ ����
class B2 implements A2 {

	// �������̽��� �߻�޼��带 ����
	@Override
	public void make() {
		System.out.println("B2�� make");
	}

	@Override
	public void play() {

		System.out.println("B2�� play");
	}

}

public class InterMain02 {
	public static void main(String[] args) {
		B2 bp = new B2();
		bp.make();
		bp.play();
	}
}
//Ŭ������ ���߻���� �ƴ� ���ϻ��
//�������̽����� ���, �������̽��� Ŭ������ ����
//�������̽��� Ŭ������ �����Ҷ� ���߱�������