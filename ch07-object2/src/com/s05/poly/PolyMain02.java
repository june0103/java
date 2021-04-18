package com.s05.poly;

class A {
	public void make() {
		System.out.println("make메서드");
	}

}

class B extends A {
	public void play() {
		System.out.println("play메서드");
	}

}

public class PolyMain02 {
	public static void main(String[] args) {
		B bp = new B();
		bp.make();
		bp.play();

		A ap = bp; // 자식클래스타입 -> 부모클래스타입 형변환
					// 업캐스팅, 자동적으로 형변환

		ap.make();
		// 호출범위를 벗어나 호출불가
		// ap.play();

		B bp2 = (B) ap; // 부모클래스타입 -> 자식클래스타입 형변환
						// 다운캐스팅, 명시적으로 형변환
		bp2.make();
		bp2.play();

		for (int i = 0; i <= 10; i++) {
			if (i % 2 == 1) {
				System.out.print(i + " ");
			}
		}

	}
}
