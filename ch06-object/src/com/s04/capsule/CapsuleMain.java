package com.s04.capsule;

class Capsule {
	// 은닉화
	// private : 같은 클래스에서만 호출 가능
	private int a;

	// 캡슐화
	public void setA(int n) {
		if (n >= 0) {
			a = n;
		} else {
			System.out.println("음수는 허용되지 않습니다.");
		}

	}

	public int getA() {
		return a;
	}

}

public class CapsuleMain {
	public static void main(String[] args) {
		Capsule cap = new Capsule();
		// a변수가 private으로 지정되어 다른 클래스에서 호출 불가능하다
		// cap.a = 100;

		// 데이터 저장
		cap.setA(100);
		// 데이터 호출
		System.out.println(cap.getA());

		// 음수저장
		cap.setA(-100);
		System.out.println(cap.getA());
	}
}
