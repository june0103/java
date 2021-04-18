public class WhileMain06 {

	public static void main(String[] args) {
		
		/* 실습
		 * 커피 아메리카노 4,000
		 * 마실 커피 수량 정하고 돈을 지불
		 * 질부한 돈에서 발생한 거스름돈 출력
		 * 상품의 총 비용보다 지불한 돈이 적어서 구매할 수 없을 경우
		 * "금액이 부족합니다." 출력하고 다시 지불 시작
		 *  출력예시
		 *  구매 수량 입력 : 2
		 *  내가 지불한 금액 : 10000
		 *  거스름돈 : 2000원
		 *  
		 *  구매 수량 입력 : 2
		 *  내가 지불한 금액 : 5000
		 *  3000원이 부족합니다.
		 */
		
		int price = 4000;
		int balance = 0;
		int quantity,payment,total;
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		while(true)
		{
			System.out.print("구매 수량 입력 : ");
			quantity = input.nextInt();
			total = price * quantity;
			System.out.print("내가 지불한 금액 : ");
			payment = input.nextInt();
			balance = payment - total;
			
			
			if(payment >= total) {
				
				System.out.printf("거스름돈 : %,d원%n",balance);
				break;
			}
			else {
				System.out.printf("%,d원이 부족합니다.%n",-balance);
			}			
		}
		input.close();
		
	}
	
}
