package com.s07.thistest;

public class AnimalMain {

	public static void main(String[] args) {

		Animal a = new Animal();
		a.setName("기린");
		a.setAge(2);
		a.setFly(false);

		if (a.getFly()) {

			a.f = "가능";

		} else {
			a.f = "불가능";
		}

		System.out.println("이름 : " + a.getName());
		System.out.println("나이 : " + a.getAge());
		System.out.println("비행여부 : " + a.f);

		System.out.println("--------------");

		Animal a2 = new Animal("독수리", 5, true);
		System.out.println("이름 : " + a2.getName());
		System.out.println("나이 : " + a2.getAge());
		if (a2.getFly())
			System.out.println("비행여부 : 가능");

		System.out.println(printFly(a2.getFly()));

	}

	private static String printFly(boolean fly) {

		return fly ? "가능" : "불가능";

	}

}
