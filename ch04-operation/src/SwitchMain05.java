public class SwitchMain05 {

	public static void main(String[] args) {

		//계산기. 2개의 정수를 입력. 연산자(+,-,*,/,%) 입력.결과값 출력
		/* 예시
		 * 첫번째 수 : 10
		 * 연산자 : -
		 * 두번재 수 : 5
		 * 결과 : 10 - 5 = 5
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int first, second, result = 0;
		String operator;
		
		System.out.print("첫번째 수 : ");
		first = input.nextInt();
		System.out.print("연산자 : ");
		operator = input.next();
		System.out.print("두번째 수 : ");
		second = input.nextInt();
		
		switch(operator) {
		case "+" : result = first + second; break;
		case "-" : result = first - second; break;
		case "*" : result = first * second; break;
		case "/" :
			switch(second) {
			case 0 : System.out.println("0으로 나눌 수 없습니다."); System.exit(0);
			default :  result = first / second;
			}
			break;
		case "%" : 
			switch(second) {
			case 0 : System.out.println("0으로 나눌 수 없습니다."); System.exit(0);
			default :  result = first % second;
			}
			break;
		default : System.out.println("잘못된 연산자 입력");
		System.exit(0);
		}
		
		System.out.printf("결과 : %d %s %d = %d", first,operator,second,result);
		input.close();
		
	}

}
