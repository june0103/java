package com.s01.basic;
class Account {

	// 실습
	// Account
	// 멤버변수 : 계좌번호(account_num), 예금주(name), 잔고(balance)
	// 멤버메서드 : 예금하기(deposite), 출금하기(withdraw), 계좌정보표시하기(printAccount)

	int account_num, balance;
	String name;

	public void deposite(int a) {
		balance += a;
	}

	public void withdraw(int a) {
		balance -= a;

	}

	public void printAccount() {

		System.out.println("계좌번호 : " + account_num);
		System.out.println("예금주 : " + name);
		System.out.printf("잔고 : %,d%n", balance);

	}

}

public class BankMain {

	public static void main(String[] args) {

		Account b = new Account();

		b.account_num = 1013451245;
		b.name = "홍길동";
		b.balance = 0;

		System.out.println("=====계좌정보=====");
		b.printAccount();
		System.out.println("=====입금하기=====");
		System.out.println("입금금액 : 4,000원");
		b.deposite(4000);
		System.out.println("=====계좌정보=====");
		b.printAccount();
		System.out.println("=====출금하기=====");
		System.out.println("입금금액 : 1,000원");
		b.withdraw(1000);
		System.out.println("=====계좌정보=====");
		b.printAccount();
	}

}
