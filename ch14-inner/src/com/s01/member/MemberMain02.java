package com.s01.member;

class Outer2 {
	// �������
	private int x = 100;

	// ��� ����Ŭ����
	class Inner2 {
		private int y = 200;

		// ����޼���
		public void make() {
			// Outer2�� x�� ����� �ν��ϱ� �빮�� ȣ�� ����
			System.out.println("x = " + x);
			System.out.println("y = " + y);
		}
	}
}

public class MemberMain02 {
	public static void main(String[] args) {
		Outer2 ot = new Outer2();
		Outer2.Inner2 oi = ot.new Inner2();

		oi.make();
	}
}
