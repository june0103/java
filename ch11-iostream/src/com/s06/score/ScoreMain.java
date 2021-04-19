package com.s06.score;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScoreMain {

	BufferedReader br;
	ArrayList<Score> scoreList;

	public ScoreMain() {
		scoreList = new ArrayList<Score>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}

	}

	// 메뉴
	public void callMenu() throws IOException {
		while (true) {

			try {
				System.out.print("1.성적입력 2.성적보기 3.파일생성 4.종료 >");
				int num = Integer.parseInt(br.readLine());
				if (num == 1) {
					input(); // 성적입력
				} else if (num == 2) {
					output(); // 성적보기
				} else if (num == 3) {
					createFile();
				} else if (num == 4) {
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("숫자만 허용!");
			}

		}
	}

	// 성적입력
	public void input() throws IOException {
		Score sc = new Score();
		System.out.print("이름:");
		sc.setName(br.readLine());

		sc.setKorean(parseInputData("국어점수:"));
		sc.setEnglish(parseInputData("영어점수:"));
		sc.setMath(parseInputData("수학점수:"));

//		System.out.print("국어점수:");
//		sc.setKorean(Integer.parseInt(br.readLine()));
//		System.out.print("영어점수:");
//		sc.setEnglish(Integer.parseInt(br.readLine()));
//		System.out.print("수학점수:");
//		sc.setMath(Integer.parseInt(br.readLine()));
		// score를 arraylist에 저장
		scoreList.add(sc);
	}

	// 성적출력
	public void output() {
		// System.out.println("이름\t국어점수\t국어등급\t영어점수\t영어등급\t수학점수\t수학등급\t총점수\t평균");
		System.out.println("이름\t국어점수\t영어점수\t수학점수\t총점수\t평균\t등급");

		for (Score sc : scoreList) {

			System.out.printf("%s\t", sc.getName());
			System.out.printf("%d\t", sc.getKorean());
			// System.out.printf("%s\t", sc.makeGrade());
			System.out.printf("%d\t", sc.getEnglish());
			// System.out.printf("%s\t", sc.makeGrade());
			System.out.printf("%d\t", sc.getMath());
			// System.out.printf("%s\t", sc.makeGrade());
			System.out.printf("%d\t", sc.makeSum());
			System.out.printf("%.2f\t", sc.makeAvg());
			System.out.printf("%s\t\n", sc.makeGrade());
		}
	}

	// 파일생성
	public void createFile() {
		FileWriter fw = null;

		try {
			fw = new FileWriter("Score.txt");

			for (Score sc : scoreList) {

				fw.write("이름 : " + sc.getName() + "\t"); // 데이터를 버퍼에 저장
				fw.write(String.valueOf("국어점수 : " + sc.getKorean()) + "\t"); // 데이터를 버퍼에 저장
				fw.write("영어점수 : " + sc.getEnglish() + "\t"); // 데이터를 버퍼에 저장
				fw.write("수학점수 : " + sc.getMath() + "\t"); // 데이터를 버퍼에 저장
				fw.write("총점수 : " + sc.makeSum() + "\t"); // 데이터를 버퍼에 저장
				fw.write("평균 : " + String.format("%.2f", sc.makeAvg()) + "\t"); // 데이터를 버퍼에 저장
				fw.write("등급 : " + sc.makeGrade() + "\n"); // 데이터를 버퍼에 저장

				// 버퍼를 비우고 데이터를 파일로 전송
				fw.flush();

			}

			System.out.println("파일을 생성");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
				}
		}
	}

	// 성적 범위 체크(0~100)
	public int parseInputData(String cours) throws IOException {
		while (true) {
			System.out.println(cours);
			try {
				int num = Integer.parseInt(br.readLine());

				if (num < 0 || num > 100) {
					throw new ScoreValueException("0~100사이의 값만 입력");
				}
				return num;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력하세요!");
			} catch (ScoreValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new ScoreMain();
	}

}
