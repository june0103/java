package com.s04.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			// ����Ʈ��Ʈ�� -> ���ڽ�Ʈ��
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("����� �̸� :");
			String name = br.readLine();
			System.out.println(name);

			System.out.print("����� ���� :");
			// string -> int
			int age = Integer.parseInt(br.readLine());
			System.out.println(age);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}

		}
	}
}
