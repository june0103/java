package com.s07.thistest;

public class AnimalMain {

	public static void main(String[] args) {

		Animal a = new Animal();
		a.setName("�⸰");
		a.setAge(2);
		a.setFly(false);

		if (a.getFly()) {

			a.f = "����";

		} else {
			a.f = "�Ұ���";
		}

		System.out.println("�̸� : " + a.getName());
		System.out.println("���� : " + a.getAge());
		System.out.println("���࿩�� : " + a.f);

		System.out.println("--------------");

		Animal a2 = new Animal("������", 5, true);
		System.out.println("�̸� : " + a2.getName());
		System.out.println("���� : " + a2.getAge());
		if (a2.getFly())
			System.out.println("���࿩�� : ����");

		System.out.println(printFly(a2.getFly()));

	}

	private static String printFly(boolean fly) {

		return fly ? "����" : "�Ұ���";

	}

}
