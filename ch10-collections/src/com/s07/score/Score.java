package com.s07.score;

public class Score {

	private String name; // 이름
	private int korean; // 국어점수
	private int english; // 영어점수
	private int math; // 수학점수

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int makeSum() {

		return korean + english + math;
	}

	public double makeAvg() {

		return makeSum() / 3.0;
	}

	public String makeGrade() {
		String grade;

		switch ((int) makeAvg() / 10) {
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

}
