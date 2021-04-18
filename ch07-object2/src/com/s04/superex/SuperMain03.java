package com.s04.superex;

class People{
	int a = 100;

	public People() {
		super(); //object의 default 생성자 
	}
	
}

class Child2 extends People{
	int b = 200;
	
	//생성자
	public Child2() {
		super(); //부모클래스의 default생성자
	}
}

public class SuperMain03 {
	public static void main(String[] args) {
				
	}
}
