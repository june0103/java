package com.s01.basic;

public class MethodMain03 {
	// 인자 전달 방식 : 참조 호출
	// 메서드 호출시 전달하려는 인자를 참조(객체)자료형을 사용한 경우를 의미, 주소를 복사하여 전달

	public void increase(int[] n) {
		for (int i = 0; i < n.length; i++) {
			n[i]++;
		}

	}

	public static void main(String[] args) {

		int ref1[] = { 100, 200, 300 };
		System.out.println("======데이터 변경전=======");
		for (int i = 0; i < ref1.length; i++) {
			System.out.println("ref1[" + i + "] : " + ref1[i]);
		}

		System.out.println("======데이터 변경후========");
		MethodMain03 m = new MethodMain03();
		m.increase(ref1);

		for (int i = 0; i < ref1.length; i++) {

			System.out.println("ref1[" + i + "] : " + ref1[i]);

		}
	}
}
