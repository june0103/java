package com.s05.constructor;

public class MyClass {

	// ����ȭ
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

	// ���ڰ� �ִ� ������
	public MyClass(String n, int a) {
		name = n;
		age = a;
	}

	// ���ڰ� ���� ������
	public MyClass() {

	}

	public MyClass(String n) {
		name = n;
		age = 10;
	}

}
