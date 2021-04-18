package com.s03.method;

import java.util.Scanner;

class Score {
	//
	// ������� : �̸�(name), ����(korean), ����(english), ����(math)
	// ����޼��� : ����(int getSum()), ���(double getAverage()), ���(String getGrade())
	// �������(void printScore()) -> �̸�, ����, ����, ����, ����, ���, ���

	String name;
	int korean, english, math;

	public int getSum() {

		return korean + english + math;

	}

	public double getAverage() {

		return getSum() / 3.0;

	}

	public String getGrade() {
		String grade;

		switch ((int) (getAverage() / 10)) {
		case 10:
		case 9:
			grade = "A";
			break;
		case 8:
			grade = "B";
			break;
		case 7:
			grade = "C";
			break;
		case 6:
			grade = "D";
			break;
		default:
			grade = "F";
		}
		return grade;
	}

	public void printScore() {

		System.out.println("�̸� : " + name);
		System.out.println("���� : " + korean);
		System.out.println("���� : " + english);
		System.out.println("���� : " + math);
		System.out.println("���� : " + getSum());
		System.out.printf("��� : %.2f%n", getAverage());
		System.out.println("��� : " + getGrade());

	}

}

public class ScoreMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Score score = new Score();

		System.out.print("�̸� �Է�> ");
		score.name = sc.next();
		System.out.print("�������� �Է�> ");
		score.korean = sc.nextInt();
		System.out.print("�������� �Է�> ");
		score.english = sc.nextInt();
		System.out.print("�������� �Է�> ");
		score.math = sc.nextInt();

		score.getSum();
		score.getAverage();
		score.printScore();

		sc.close();

	}
}
