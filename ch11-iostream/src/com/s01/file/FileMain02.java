package com.s01.file;

import java.io.File;
import java.io.IOException;

public class FileMain02 {
	public static void main(String[] args) {
		// 절대경로 지정
		// String path = "C:\\javaWork\\sample.txt";

		// 상대경로 지정
		String path = "sample.txt"; // 현재프로젝트에 생성됨
		String path2 = "example.txt";
		
		File f1 = new File(path);
		System.out.println("====파일생성====");
		try {
			System.out.println(f1.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("====파일정보====");
		System.out.println("절대 경로 : " + f1.getAbsolutePath());
		System.out.println("상대 경로 : " + f1.getPath());
		System.out.println("디렉토리명 : "+f1.getParent());
		System.out.println("파일명 : "+f1.getName());
		
		System.out.println("====파일명 변경====");
		File f2 = new File(path2);
		System.out.println(f1.renameTo(f2));
		
		System.out.println("====파일삭제====");
		//delete() : 삭제할 수 있으면 삭제하고 true 반환
		//			 삭제가 불가능하면 false 반환
		if(f2.delete()) {
			System.out.println(f2.getName()+" 파일 삭제");
		}else {
			System.out.println("파일을 삭제하지 못 했습니다.");
		}
		
	}
}
