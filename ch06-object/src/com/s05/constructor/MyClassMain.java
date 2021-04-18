package com.s05.constructor;

public class MyClassMain {
	public static void main(String[] args) {

		MyClass mc = new MyClass();
		mc.setName("홍길동");
		mc.setAge(20);
		System.out.println(mc.getName() + "  " + mc.getAge());

		System.out.println("-------------------");

		MyClass mc2 = new MyClass("박문수", 40);
		System.out.println(mc2.getName() + " " + mc2.getAge());

		System.out.println("-------------------");

		MyClass mc3 = new MyClass("장영실");

		System.out.println(mc3.getName() + " " + mc3.getAge());
	}
}
