package com.s01.enumex;

enum Item {
	ADD, DEL, SEARCH, CANCEL
}

public class EnumMain03 {
	public static void main(String[] args) {
		System.out.println(Item.ADD);
		System.out.println(Item.DEL);
		System.out.println(Item.SEARCH);
		System.out.println(Item.CANCEL);
		System.out.println("------------");

		// values()메서드는 열거 타입의 모든 열거 객체들을 배열로 만들어 반환한다
		for (Item n : Item.values()) {
			// ( 문자열상수 : 순서값)
			System.out.println(n + ":" + n.ordinal());
		}
	}
}
