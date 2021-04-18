package com.p1;

import com.p2.PackTwo;

public class PackMain {
	public static void main(String[] args) {
		// com.p1.PackOne p1 = new com.p1.PackOne();
		// 같은 패키지의 클래스는 피키지를 생략하고 클래슴나 명시가능
		PackOne p1 = new PackOne();

		// 다른 패키지이기 때문에 클래스를 명시할 때 패키지를 함께 명시.
		// 하지만 코드가 길어져 패키지를 import
		// 패키지를 포함해서 클래슬르 등록하면 클래스를 호출할 때 패키지를 생략하고 호출해도 인식가능
		// com.p2.PackTwo p2 = new com.p2.PackTwo();
		PackTwo p2 = new PackTwo();

	}

}
