public class WhileMain05 {

	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int balance = 0;
		
		while(true)	{
			System.out.println("-----------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("-----------------------------");
			
			System.out.print("메뉴 선택> ");
			int num = input.nextInt();
			
			if(num ==1 ) {
				System.out.println("예금액> ");
				balance += input.nextInt()	; //누적
			}
			else if(num ==2 ) {
				System.out.println("출금액> ");
				balance -= input.nextInt(); //차감
				
			}
			else if(num ==3 ) {
				System.out.printf("잔고 : %,d원%n",balance);
			}
			else if(num ==4 ) {
				System.out.println("프로그램 종료");
				break;
				
			}
			else {
				System.out.println("다시입력해주세요.");
			}
		}
		
		
		input.close();
	}
	
}
