package com.s01.file;

import java.io.File;

public class FileMain01 {
	public static void main(String[] args) {
		String path = "C:\\";
		File f = new File(path);

		if (!f.exists() || !f.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다");
			//프로그램 종료
			System.exit(0);
		}
		
		//인자로 전달된 디렉토리의 하위 디렉토리 및 파일 정보를 File배열로 반환
		
		File[] files = f.listFiles();
		
		for(int i = 0; i<files.length; i++) {
			File f2 = files[i];
			if(f2.isDirectory()) {
				//디렉토리
				System.out.println("["+f2.getName()+"]");
			}else {
				//파일
				System.out.print(f2.getName());
				System.out.printf("(%,dbytes)\n",f2.length()); //파일용량
			}
		}

	}
}
