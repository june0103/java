package com.s04.superex;

class Parent{
	int a = 100;
	public void play() {
		System.out.println("Parent�� Play �޼���");
	}
}

class Child extends Parent{
	int a = 200;
	
	public Child() {
		System.out.println(super.a);
		super.play();
		System.out.println("=======================");
	}
	
	@Override
	public void play() {
		System.out.println("Child�� play �޼���");
	}
}

public class SuperMain02 {
	public static void main(String[] args) {
		Child ch = new Child();
		System.out.println(ch.a);
		ch.play();
	}


}
