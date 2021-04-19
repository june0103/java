package com.s06.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductMain {
	ArrayList<Product> list;
	BufferedReader br;

	public ProductMain() {
		list = new ArrayList<Product>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}
	}

	public void callMenu() throws IOException {
		while (true) {
			try {
				System.out.print("1.상품입력 2.상품목록보기 3.종료 >");

				int num = Integer.parseInt(br.readLine());
				if (num == 1) {
					input(); // 상품입력
				} else if (num == 2) {
					output(); // 상품목록보기
				} else if (num == 3) {
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("숫자만 허용!");
			}
		}
	}

	// 상품정보 입력
	public void input() throws IOException {
		Product p = new Product();
		System.out.print("상품명:");
		p.setName(br.readLine());
		System.out.print("상품번호:");
		p.setNum(br.readLine());
		System.out.print("가격:");
		p.setPrice(Integer.parseInt(br.readLine()));
		System.out.print("메이커:");
		p.setMaker(br.readLine());
		System.out.print("재고:");
		p.setStock(Integer.parseInt(br.readLine()));

		// product를 arraylist에 저장
		list.add(p);
	}

	// 상품정보 호출
	public void output() {
		System.out.println("상품리스트 : 총상품수(" + list.size() + ")");
		System.out.println("상품명\t상품번호\t가격\t메이커\t재고수");

		for (Product pt : list) {
			System.out.printf("%s\t", pt.getName());
			System.out.printf("%s\t", pt.getNum());
			System.out.printf("%,d\t", pt.getPrice());
			System.out.printf("%s\t", pt.getMaker());
			System.out.printf("%,d\t\n", pt.getStock());
		}
	}

	public static void main(String[] args) {
		new ProductMain();
	}

}
