package com.s01.list;

import java.util.ArrayList;

class A {

}

class B {
}

public class ArrayListMain01 {
	public static void main(String[] args) {
		ArrayList a1 = new ArrayList();
		a1.add(new A()); // A->Object
		a1.add(new B()); // B->object
		a1.add("ȫ�浿"); // String->object
		a1.add(100);// Integer->object

		// ArrayList�� ����� ����� ���
		System.out.println(a1);
	}

}
