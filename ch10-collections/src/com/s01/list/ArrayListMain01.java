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
		a1.add("홍길동"); // String->object
		a1.add(100);// Integer->object

		// ArrayList에 저장된 요소의 목록
		System.out.println(a1);
	}

}
