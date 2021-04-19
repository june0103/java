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

	// �޴�
	public void callMenu() throws IOException {
		while (true) {

			try {
				System.out.print("1.�����Է� 2.�������� 3.���ϻ��� 4.���� >");
				int num = Integer.parseInt(br.readLine());
				if (num == 1) {
					input(); // �����Է�
				} else if (num == 2) {
					output(); // ��������
				} else if (num == 3) {
					createFile();
				} else if (num == 4) {
					System.out.println("���α׷� ����");
					break;
				} else {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("���ڸ� ���!");
			}

		}
	}

	// �����Է�
	public void input() throws IOException {
		Score sc = new Score();
		System.out.print("�̸�:");
		sc.setName(br.readLine());

		sc.setKorean(parseInputData("��������:"));
		sc.setEnglish(parseInputData("��������:"));
		sc.setMath(parseInputData("��������:"));

//		System.out.print("��������:");
//		sc.setKorean(Integer.parseInt(br.readLine()));
//		System.out.print("��������:");
//		sc.setEnglish(Integer.parseInt(br.readLine()));
//		System.out.print("��������:");
//		sc.setMath(Integer.parseInt(br.readLine()));
		// score�� arraylist�� ����
		scoreList.add(sc);
	}

	// �������
	public void output() {
		// System.out.println("�̸�\t��������\t������\t��������\t������\t��������\t���е��\t������\t���");
		System.out.println("�̸�\t��������\t��������\t��������\t������\t���\t���");

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

	// ���ϻ���
	public void createFile() {
		FileWriter fw = null;

		try {
			fw = new FileWriter("Score.txt");

			for (Score sc : scoreList) {

				fw.write("�̸� : " + sc.getName() + "\t"); // �����͸� ���ۿ� ����
				fw.write(String.valueOf("�������� : " + sc.getKorean()) + "\t"); // �����͸� ���ۿ� ����
				fw.write("�������� : " + sc.getEnglish() + "\t"); // �����͸� ���ۿ� ����
				fw.write("�������� : " + sc.getMath() + "\t"); // �����͸� ���ۿ� ����
				fw.write("������ : " + sc.makeSum() + "\t"); // �����͸� ���ۿ� ����
				fw.write("��� : " + String.format("%.2f", sc.makeAvg()) + "\t"); // �����͸� ���ۿ� ����
				fw.write("��� : " + sc.makeGrade() + "\n"); // �����͸� ���ۿ� ����

				// ���۸� ���� �����͸� ���Ϸ� ����
				fw.flush();

			}

			System.out.println("������ ����");
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

	// ���� ���� üũ(0~100)
	public int parseInputData(String cours) throws IOException {
		while (true) {
			System.out.println(cours);
			try {
				int num = Integer.parseInt(br.readLine());

				if (num < 0 || num > 100) {
					throw new ScoreValueException("0~100������ ���� �Է�");
				}
				return num;
			} catch (NumberFormatException e) {
				System.out.println("���ڸ� �Է��ϼ���!");
			} catch (ScoreValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new ScoreMain();
	}

}
