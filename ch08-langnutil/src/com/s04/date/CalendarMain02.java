package com.s04.date;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarMain02 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();

		System.out.println("��� ������ ���� �Է�(ex ��:2021, ��:1");
		System.out.print("���� : ");
		int year = input.nextInt();
		System.out.print("�� : ");
		int month = input.nextInt();

		System.out.println("       [" + year + "�� " + month + "��]");
		System.out.println("---------------------------");
		System.out.println(" ��  ��  ȭ  �� ��  �� ��");

		// ����ϴ� ����, ��, �� ����. ���� ������ 0~11�̱� ������ �Է¿�-1
		// ���� �޷��� 1�Ϻ��� �����ϱ� ������ 1�Ϸ� ����
		// (��, ��, ��)
		cal.set(year, month - 1, 1);
		// 1���� ���� ���ϱ�(1 - �Ͽ��� ~ 7 - �����)
		int week = cal.get(Calendar.DAY_OF_WEEK);
		// ���� ������ ���� ���ϱ�
		int lastOfDate = cal.getActualMaximum(Calendar.DATE);

		// �޷� ���� ù�� �����ϴ³����� ���� �Է�
		for (int i = 1; i < week; i++) {
			System.out.printf("%3s", " ");
		}
		// 1��~���� ����-�� ��¥���� �ݺ����� �̿��ؼ� ǥ��
		for (int i = 1; i <= lastOfDate; i++) {
			System.out.printf("%3d", i);
			if (week % 7 == 0) {
				System.out.println();
			}
			week++;

		}

		input.close();
	}
}
