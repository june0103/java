package com.s05.constructor;

//���� ��Ű���ȿ� Ŭ�������� ������ �����߻�, Ŭ�������� �ٸ��� �ؾ��Ѵ�
class Car2 {
	String color;
	String gearType;
	int door;

	// ���ڰ� �ִ� ������
	public Car2(String c, String g, int d) {
		color = c;
		gearType = g;
		door = d;
	}

	// ���ڰ� ���� ������, �⺻�����ڴ� �����ڰ� ������ �������������� �����ڰ� ��õǾ�������� �������� �ʴ´�. �׷��� ����ʿ�� ���������
	// �Ѵ�
	public Car2() {

	}

}

public class CarMain02 {
	public static void main(String[] args) {
		Car2 C1 = new Car2("Blue", "auto", 4);

		System.out.println(C1.color);
		System.out.println(C1.gearType);
		System.out.println(C1.door);

		System.out.println("----------------");

		Car2 c2 = new Car2();
		c2.color = "black";
		c2.gearType = "maneal";
		c2.door = 4;

		System.out.println(c2.color);
		System.out.println(c2.gearType);
		System.out.println(c2.door);

	}

}
