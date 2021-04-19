package com.s01.exception;

public class ExceptionMain02 {

	public static void main(String[] args) {
		// 예외처리하기
		int array[] = { 10, 20, 30 };
		for (int i = 0; i <= array.length; i++) {
			// 예외처리
			// 예외가 발생해도 정상 종료될 수 있도록 프로그램적으로 처리하는것

			try {
				// 예외가 발생할 가능성이 있는 코드를 명시
				System.out.println("array[" + i + "]:" + array[i]);

				// 예외발생시 생성되는 예외 객체 타입
			} catch (ArrayIndexOutOfBoundsException e) {
				// 객체에서 제공한느 예외문구
				e.printStackTrace();
				// System.out.println("없는 인덱스 호출");
			} // end of try ~ catch
		} // end of for
		System.out.println("프로그램끝");
	}

}
