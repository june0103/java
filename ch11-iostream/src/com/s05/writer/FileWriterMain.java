package com.s05.writer;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterMain {
	public static void main(String[] args) {
		FileWriter fw = null;

		try {
			fw = new FileWriter("fileWriter.txt");

			String message = "�ȳ��ϼ���! FileWriter �׽�Ʈ";
			fw.write(message); // �����͸� ���ۿ� ����
			// ���۸� ���� �����͸� ���Ϸ� ����
			fw.flush();

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
}
