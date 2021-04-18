package com.s05.constructor;

public class MyClass {

	// 은닉화
	private String name;
	private int age;

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setAge(int a) {
		age = a;
	}

	public int getAge() {
		return age;
	}

	// 인자가 있는 생성자
	public MyClass(String n, int a) {
		name = n;
		age = a;
	}

	// 인자가 없는 생성자
	public MyClass() {

	}

	public MyClass(String n) {
		name = n;
		age = 10;
	}

}
