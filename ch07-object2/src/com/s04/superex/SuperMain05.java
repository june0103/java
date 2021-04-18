package com.s04.superex;

class Parent2{
	int a;
	int b;
	
	//인자있는 부보클래스 생성자
	public Parent2(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
}

class Child3 extends Parent2{
	public Child3(int a, int b) {//부모클래스의 인자가 있는 생성자를 명시적으로 호출해야 객체 생성이 가능하다.
	
		super(a,b); //앞에와 다르게 직접 값을 초기화시키지않고 자식클래스의 생성자를 인자가 있는 생성자로 만드는 방법도 있다.
	}
}

public class SuperMain05 {
	public static void main(String[] args) {
		
		Child3 c = new Child3(300, 400);
		System.out.println(c.a + " " + c.b);
		
	}

}
