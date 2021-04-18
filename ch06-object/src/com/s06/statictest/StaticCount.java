package com.s06.statictest;

public class StaticCount {
	// 인스턴스 변수
	int c;
	// static(클래스) 변수
	static int count;

	public StaticCount() {
		c++;
		count++;
	}

}
