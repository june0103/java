package com.s09.inter;

//�������̽��� ǥ��ȭ�Ҷ� ���̻��. �������̽��� ���븦 ����� Ŭ������ ����
//Ŭ������ ���߻���� �ƴ� ���ϻ��
//�������̽����� ���, �������̽��� Ŭ������ ����
//�������̽��� Ŭ������ �����Ҷ� ���߱�������
interface Inter1 {
	// �߻�޼���
	public abstract int getA();

}

interface Inter2 extends Inter1 {
	// �߼��޼��� getA()�� �������ִ°ŷ� �ν�
	public int getB(); // abstract�� ����� �������̽����� �߻�޼���� �ν�. abstract��������

}

interface Inter3 {
	// �߻�޼���
	public int getC();
}

//�������̽��� Ŭ������ ����
class InterTest implements Inter2, Inter3 {
	// �����Ǵ� ��� �������̽� �߻�޼��� ����

	@Override
	public int getA() {

		return 10;
	}

	@Override
	public int getB() {

		return 20;
	}

	@Override
	public int getC() {

		return 30;
	}
}

public class InterMain03 {

	public static void main(String[] args) {

		InterTest it = new InterTest();
		System.out.println(it.getA());
		System.out.println(it.getB());
		System.out.println(it.getC());

	}

}
