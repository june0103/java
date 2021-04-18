package com.s07.thistest;

public class Animal {
	private String name;
	private int age;
	private boolean fly;
	String f;

	public Animal() {
		// TODO Auto-generated constructor stub
	}

	public Animal(String name, int age, boolean fly) {

		this.name = name;
		this.age = age;
		this.fly = fly;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setFly(boolean fly) {
		this.fly = fly;
	}

	public boolean getFly() {

		return fly;
	}

}
