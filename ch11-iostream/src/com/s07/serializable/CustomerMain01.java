package com.s07.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CustomerMain01 {
	public static void main(String[] args) {
		System.out.println("객체직렬화");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		// 직렬화할 객체 생성
		Customer c = new Customer("홍길동");

		try {
			fos = new FileOutputStream("object.ser");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(c); // 객체 직렬화 수행

			System.out.println("객체 직렬화 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
		}
	}
}
