package com.s03.method;

import java.util.Scanner;

class Score {
	//
	// 멤버변수 : 이름(name), 국어(korean), 영어(english), 수학(math)
	// 멤버메서드 : 총점(int getSum()), 평균(double getAverage()), 등급(String getGrade())
	// 점수출력(void printScore()) -> 이름, 국어, 영어, 수학, 총점, 평균, 등급

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

		System.out.println("이름 : " + name);
		System.out.println("국어 : " + korean);
		System.out.println("영어 : " + english);
		System.out.println("수학 : " + math);
		System.out.println("총점 : " + getSum());
		System.out.printf("평균 : %.2f%n", getAverage());
		System.out.println("등급 : " + getGrade());

	}

}

public class ScoreMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Score score = new Score();

		System.out.print("이름 입력> ");
		score.name = sc.next();
		System.out.print("국어점수 입력> ");
		score.korean = sc.nextInt();
		System.out.print("영어점수 입력> ");
		score.english = sc.nextInt();
		System.out.print("수학점수 입력> ");
		score.math = sc.nextInt();

		score.getSum();
		score.getAverage();
		score.printScore();

		sc.close();

	}
}
