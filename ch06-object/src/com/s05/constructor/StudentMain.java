package com.s05.constructor;

class Student {
	int korean, english, math, history, num, sum, avg;

	public Student(int k, int e, int m, int n) {
		korean = k;
		english = e;
		math = m;
		num = n;
	}

	public Student(int k, int e, int m, int h, int n) {
		korean = k;
		english = e;
		math = m;
		history = h;
		num = n;
	}

	public int getTotal() {
		switch (num) {
		case 3:
			sum = korean + english + math;
			break;
		case 4:
			sum = korean + english + math + history;
			break;
		}
		return sum;
	}

	public int getAverage() {
		avg = sum / num;
		return avg;
	}
}

public class StudentMain {
	public static void main(String[] args) {

		Student student1 = new Student(98, 97, 96, 3);
		System.out.println("3°³ °ú¸ñ ÃÑÁ¡ : " + student1.getTotal());
		System.out.println("3°³ °ú¸ñ Æò±Õ : " + student1.getAverage());
		System.out.println("-----------------------------------");

		Student student2 = new Student(98, 97, 96, 95, 4);

		System.out.println("4°³ °ú¸ñ ÃÑÁ¡ : " + student2.getTotal());
		System.out.println("4°³ °ú¸ñ Æò±Õ : " + student2.getAverage());
	}

}
