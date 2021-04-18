package com.s01.extention;

//부모클래스
class Parent { //extends object 생략
			//클래스를 정의하면 자동적으로 object가 상속됨
	int a = 500;
}

//자식클래스
class Child extends Parent {
	int b = 200;
}

public class ExtentionMain01 {
	public static void main(String[] args) {

		Child ch = new Child();
		Parent p = new Parent();
		
		System.out.println(ch.a);
		
		ch.a = 2;
		System.out.println(p.a);
		System.out.println(ch.a);
	}
}
