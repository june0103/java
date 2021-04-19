package com.s01.file;

import java.io.File;
import java.io.IOException;

public class FileMain02 {
	public static void main(String[] args) {
		// ������ ����
		// String path = "C:\\javaWork\\sample.txt";

		// ����� ����
		String path = "sample.txt"; // ����������Ʈ�� ������
		String path2 = "example.txt";
		
		File f1 = new File(path);
		System.out.println("====���ϻ���====");
		try {
			System.out.println(f1.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("====��������====");
		System.out.println("���� ��� : " + f1.getAbsolutePath());
		System.out.println("��� ��� : " + f1.getPath());
		System.out.println("���丮�� : "+f1.getParent());
		System.out.println("���ϸ� : "+f1.getName());
		
		System.out.println("====���ϸ� ����====");
		File f2 = new File(path2);
		System.out.println(f1.renameTo(f2));
		
		System.out.println("====���ϻ���====");
		//delete() : ������ �� ������ �����ϰ� true ��ȯ
		//			 ������ �Ұ����ϸ� false ��ȯ
		if(f2.delete()) {
			System.out.println(f2.getName()+" ���� ����");
		}else {
			System.out.println("������ �������� �� �߽��ϴ�.");
		}
		
	}
}
