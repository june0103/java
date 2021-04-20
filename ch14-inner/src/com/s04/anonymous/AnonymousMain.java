package com.s04.anonymous;

//�θ�۷���
class Inner6 {
	public void disp() {
		System.out.println("disp �޼���");
	}
}

public class AnonymousMain {

	public void make() {
//		// ���� ����Ŭ����
//		class Inner extends Inner6 {
//			@Override
//			public void disp() {
//				System.out.println("���� ����Ŭ������ disp");
//			}
//		}
//		Inner i = new Inner();
//		i.disp();

		// �͸� ���� Ŭ����
		// Ŭ���� ���� + ��ü ����
		Inner6 i = new Inner6() {
			@Override
			public void disp() {
				System.out.println("�͸� ����Ŭ������ disp");
			}
		};
		i.disp();

	}

	public static void main(String[] args) {

		AnonymousMain am = new AnonymousMain();
		am.make();
	}
}
