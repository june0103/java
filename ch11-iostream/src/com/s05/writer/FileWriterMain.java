package com.s05.writer;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterMain {
	public static void main(String[] args) {
		FileWriter fw = null;

		try {
			fw = new FileWriter("fileWriter.txt");

			String message = "안녕하세요! FileWriter 테스트";
			fw.write(message); // 데이터를 버퍼에 저장
			// 버퍼를 비우고 데이터를 파일로 전송
			fw.flush();

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
}
