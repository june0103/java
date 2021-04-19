package com.s07.score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.s06.product.Product;

public class ScoreMain {

	BufferedReader br;
	ArrayList<Score> list;

	// ������
	public ScoreMain() {
		list = new ArrayList<Score>();
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
				System.out.print("1.�����Է� 2.�������� 3.���� >");

				int num = Integer.parseInt(br.readLine());
				if (num == 1) {
					input(); // �����Է�
				} else if (num == 2) {
					output(); // ��������
				} else if (num == 3) {
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

	// ���� �Է�
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
		list.add(sc);

	}

	// ���� ���
	public void output() {

		// System.out.println("�̸�\t��������\t������\t��������\t������\t��������\t���е��\t������\t���");
		System.out.println("�̸�\t��������\t��������\t��������\t������\t���\t���");

		for (Score sc : list) {

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

	// ���� �Է½� 0~100 ������ �Է��ϴ� ����üũ
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
