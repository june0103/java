package com.s02.mathex;

import java.util.Random;

public class RandomMain {
	public static void main(String[] args) {

		String gift[] = { "스마트폰", "TV", "냉장고", "꽝" };

		// 0.0 ~ 1.0 미만의 난수 발생
		double ran = Math.random();
		System.out.println(ran);

		int index = (int) (ran * 4);
		System.out.println(index);
		System.out.println(gift[index]);

		String luck[] = { "로또당첨", "즐거운 여행	", "맛집 투어", "피곤해!!" };

		Random rd = new Random();

		// 0부터 인자로 전달된 값의 전까지의 범위로 난수 발생
		index = rd.nextInt(4);// 0~3
		System.out.println(index);
		System.out.println(luck[index]);
	}
}
