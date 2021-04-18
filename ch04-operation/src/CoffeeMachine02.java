public class CoffeeMachine02 {

	public static void main(String[] args) {

		/*
		 * 실습 커피를 주문하는 커피 머신에서 커피를 주문하고 돈을 지불하면 보유한 동전의 양을 체크해서 동전이 있으면 거스름돈을 지불하고 동전이
		 * 부족하면 거스름돈 부족을 출력
		 */

		java.util.Scanner input = new java.util.Scanner(System.in);

		int price = 300; // 커피가격
		int amount = 0; // 보유금액(넣은돈)
		int coin = 1000; // 보유금액(거스름돈)
	

		while (true) {
			System.out.println("-----------------------");
			System.out.print("1: 커피마시기 | 2: 종료");
			int num = input.nextInt();
			if (num == 1) {
				
				System.out.print("동전 입력>");
				//지불 금액
				int payment = input.nextInt();
				//거스름돈
				int change = payment - price;
				
				if(payment < price) {
					System.out.println("투입한 금액 부족!!");
					continue;
				}
				//거스름돈 지불여부 체크
				if(change > coin) {
					//거스름돈이 부족하면
					System.out.println("거스름돈 부족!");
					continue;
				}
				
				//거스름돈 지불 가능시
				coin -= change;
				amount += payment;
				
				//거스름돈 출력
				System.out.printf("거스름돈 : %,d원%n", change);
				System.out.println("맛 좋은 커피가 준비되었습니다.");
				
				//현재 자판기 정보 출력
				System.out.println("===현재 자판기 정보===");
				System.out.printf("동전보유금액 : %,d원%n", coin);
				System.out.printf("총 투입금액 : %d원%n", amount);
			
				
			}
			else if(num == 2) {
				System.out.println("자판기 프로그램 종료");
				break;
			}
			else {
				System.out.println("잘못 입력했습니다.");
			}
			

		}
		input.close();
	}
}
