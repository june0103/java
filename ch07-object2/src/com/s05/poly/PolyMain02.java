package com.s05.poly;

class A {
	public void make() {
		System.out.println("make�޼���");
	}

}

class B extends A {
	public void play() {
		System.out.println("play�޼���");
	}

}

public class PolyMain02 {
	public static void main(String[] args) {
		B bp = new B();
		bp.make();
		bp.play();

		A ap = bp; // �ڽ�Ŭ����Ÿ�� -> �θ�Ŭ����Ÿ�� ����ȯ
					// ��ĳ����, �ڵ������� ����ȯ

		ap.make();
		// ȣ������� ��� ȣ��Ұ�
		// ap.play();

		B bp2 = (B) ap; // �θ�Ŭ����Ÿ�� -> �ڽ�Ŭ����Ÿ�� ����ȯ
						// �ٿ�ĳ����, ��������� ����ȯ
		bp2.make();
		bp2.play();

		for (int i = 0; i <= 10; i++) {
			if (i % 2 == 1) {
				System.out.print(i + " ");
			}
		}

	}
}
