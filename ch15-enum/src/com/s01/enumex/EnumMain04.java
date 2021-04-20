package com.s01.enumex;

enum Item2 {
	// 그냥상수를 명시할 때는 세미콜론을 안해줬지만, 생성자를 만들어 데이터를 념겨줄때는 세미콜론이 필요하다
	ADD(5), DEL(11), SEARCH(2), CANCEL(22);

	// 위에 지정한 상수 값들을 저장하기 위한 공간
	private final int var;

	// 생성자
	Item2(int v) {
		var = v;
	}

	public int getVar() {
		return var;
	}

}

public class EnumMain04 {
	public static void main(String[] args) {
		System.out.println(Item2.ADD);
		System.out.println(Item2.ADD.getVar());

		for (Item2 n : Item2.values()) {
			System.out.println(n + ":" + n.getVar());
		}
	}
}
