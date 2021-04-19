package com.s07.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CustomerMain02 {
	public static void main(String[] args) {
		System.out.println("객체 역질렬화");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("object.ser");
			ois = new ObjectInputStream(fis);

			// 역직렬화 수행
			Customer m = (Customer) ois.readObject();
			System.out.println(m.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
		}
	}
}
