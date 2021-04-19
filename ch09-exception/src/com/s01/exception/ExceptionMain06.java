package com.s01.exception;

public class ExceptionMain06 {

	// throw를 이용해서 인위적으로 예외 발생
	public void methodA(String[] n) throws Exception {
		if (n.length > 0) {// 배열에 저장된 데이터가 있는겅우
			for (String s : n) {
				System.out.println(s);
			}
		} else {// 배열에 저장된 데이터가 없는경우
			// 인위적으로 예외를 발생
			throw new Exception("배열에 요소가 없습니다.");
		}

	}

	public static void main(String[] args) {
		ExceptionMain06 em = new ExceptionMain06();
		try {
			em.methodA(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
