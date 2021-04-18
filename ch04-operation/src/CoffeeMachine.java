public class CoffeeMachine {

	public static void main(String[] args) {
		
		/* 실습
		 * 커피를 주문하는 커피 머신에서 커피를 주문하고 돈을 지불하면
		 * 보유한 동전의 양을 체크해서 동전이 있으면 거스름돈을 지불하고
		 * 동전이 부족하면 거스름돈 부족을 출력
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int price = 300; //커피가격
		int amount = 0; //보유금액(넣은돈)
		int coin = 1000; //보유금액(거스름돈)
		int payment = 0;
		int change = 0;
		
		while (true) {
			System.out.println("-----------------------");
			System.out.print("1: 커피마시기 | 2: 종료");
			int num = input.nextInt();
			if(num == 1) {
				
				while(true) {		
					System.out.println("-----------------------");
					System.out.print("동전 입력> ");
					int a = input.nextInt(); //지불 금액 입력
					payment += a; //넣은금액 계산
					amount += a;
					System.out.printf("넣은금액 : %d원%n", payment);	
					if(amount >= price) { //커피를 살수 있는 돈을 넣었으면	
						
						break;						
					}						
				}
								
				//거스름돈 change 연산
				change = payment - price;
				
				if(coin >= change) {
					System.out.println("-------커피 추출 중-------");
					coin -= change; //보유금액
					payment = 0; //넣은돈 초기화
					System.out.println("커피 추출!!");
					System.out.printf("거스름돈 : %d원 반환%n", change);
					System.out.println("자판기정보");
					System.out.printf("보유금액(넣은돈) : %d원%n", amount);
					System.out.printf("보유금액(코인) : %d원%n", coin);
					
				}
				else {
					System.out.println("-----------------------");
					System.out.println("거스름돈부족");
					System.out.printf("넣은금액 : %d원%n", payment);
					System.out.printf("넣은금액%d원 반환%n", payment);
					break;					
				}
				
				
				//거스름돈 지불 여부 체크
				
				//거스름돈을 지불할 수 있으면 거스름돈 출력
				//거스름돈이 부족하면 "거스름돈 부족"
				
				//현재 자판기 정보 출력
				//보유금액, 거스름돈 출력 
				
			}
			else if(num == 2) {
				break;				
			}
			else if(coin == 0) System.out.println("거스름돈부족");
			else {
				
				System.out.println("다시 입력해 주세요.");
				
			}
		}
		
		
		input.close();
		
		
		
	}
}
