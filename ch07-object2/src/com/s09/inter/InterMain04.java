package com.s09.inter;

interface I {
	public abstract void play();// �߻�޼���

}

//�������̽����̿��ؼ� ǥ��ȭ����
class B implements I {

	// �������̽��� �߻�޼��� ����
	@Override
	public void play() {
		System.out.println("play in B class");
	}

	public void make() {
		System.out.println("make in B class");
	}
}

public class InterMain04 {
	public static void main(String[] args) {

		B bp = new B();
		bp.play();
		bp.make();

		// Ŭ����Ÿ�� -> �������̽�Ÿ�� ����ȯ
		// �ڵ������� ����ȯ
		I im = bp;
		im.play();

		// �������̽�Ÿ������ ������ �޼ҵ常 ȣ�Ⱑ��
		// ȣ�� ������ ����� ȣ�� �Ұ���
		// im.make():
		
		//�������̽�Ÿ�� -> Ŭ����Ÿ�� ����ȯ
		//��������� ����ȯ
		//������ �þ ���������� ����ȯ
		B bp2 = (B)im;
		bp2.make();
		bp2.play();

	}
}
