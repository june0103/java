package com.s03.output;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamMain {
	public static void main(String[] args) {

		FileOutputStream fos = null;
		try {
			//같은파일명을 계속쓰며 덮어쓴다
			fos = new FileOutputStream("fileOut.txt");
			
			//같은파일명을 쓰면서 이어쓰기    ("파일명", 이어쓰기여부)
			//fos = new FileOutputStream("fileOut.txt",true);
			
			String message = "Hello File! 파일에 내용을 명시하다!!";
			//String -> byte[]
			fos.write(message.getBytes());
			System.out.println("파일을 생성하고 내용을 명시함");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//자원정리
			if(fos != null)try {
				fos.close();
			} catch (IOException e) {
			}
		}
	}
}
