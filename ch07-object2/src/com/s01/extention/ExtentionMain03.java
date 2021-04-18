package com.s01.extention;

//부모클래스
class A {
	int x = 100;
	// 같은 클래스내에서만 호출 가능
	private int y = 200;
	
	public int getY() {
		return y;
	}

}

//자식클래스
class B extends A {
	int z = 300;
}

public class ExtentionMain03 {
	public static void main(String[] args) {
		B b = new B();
		System.out.println(b.x);
		// private 멤버변수는 같은 클래스내에서만 호출이 가능하다. 자식클래스에서도 x
		// System.out.println(b.y);
		System.out.println(b.getY());
		System.out.println(b.z);
	}

}
