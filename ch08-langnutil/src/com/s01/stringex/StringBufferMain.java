package com.s01.stringex;

public class StringBufferMain {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("여름 덥다!");
		System.out.println(sb);

		// 지정한 딘덱스에 문자 삽입
		sb.insert(2, '이');
		System.out.println(sb);

		// 기존문자열 뒤에 지정한 문자열을 저장
		sb.append("가을은 ");
		System.out.println(sb);

		sb.append("시원하다");
		System.out.println(sb);

		// 시작인덱스부터 끝인덱스전까지 지정한 문자열로 대체
		sb.replace(0, 3, "여행가자!!");
		System.out.println(sb);

		// 지정한 인덱스의 문자 삭제
		sb.deleteCharAt(0);
		System.out.println(sb);

		// 시작인덱스부터 끝인덱스전까지 삭제
		sb.delete(0, 3);
		System.out.println(sb);

		// StringBuffer -> String
		String str = sb.toString();
		System.out.println(str);
	}
}
