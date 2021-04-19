package com.s01.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exception05 {

	// throws������� ���
	// �޼��忡 throws ����Ŭ������ ����ϸ� �޼��峻�� try~catch���� ������ �� �ְ�
	// ���ܰ� �߻��ϸ� ���ܸ� �����ϰ� �޼��鸣 ȣ���� ���� try~catch���� ������ try~catch�� ���ܸ� �絵��

	public void printData() throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("���Է�:");
		int dan = Integer.parseInt(br.readLine());
		System.out.println(dan + "��");
		System.out.println("================");
		for (int i = 1; i <= 9; i++) {

			System.out.println(dan + "*" + i + "=" + dan * i);
		}
	}

	public static void main(String[] args) {

		Exception05 em = new Exception05();
		try {
			em.printData();
		} catch (Exception e) {
			System.out.println("���ܹ߻�");
		}

	}
}
