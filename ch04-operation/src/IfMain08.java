public class IfMain08 {

	public static void main(String[] args) {
		
		//계산기. 2개의 정수를 입력. 연산자(+,-,*,/,%) 입력.결과값 출력
		/* 예시
		 * 첫번째 수 : 10
		 * 연산자 : -
		 * 두번재 수 : 5
		 * 결과 : 10 - 5 = 5
		 */
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		int first, second, result = 0;
		String operator;
		
		System.out.print("첫번째 수 : ");
		first = sc.nextInt();
		System.out.print("연산자 : ");
		operator = sc.next();
		System.out.print("두번째 수 : ");
		second = sc.nextInt();
		
		if(operator.equals("+")){
			result = first + second;
		}
		else if(operator.equals("-")){
			result = first - second;
		}
		else if(operator.equals("*")){
			result = first * second;
		}
		else if(operator.equals("/")){
			if(second !=0 ) {
			result = first / second;
			}
			else {
				System.out.println("0으로 나눌 수 없습니다.");
				System.exit(0);
			}
		}
		else if(operator.equals("%")){
			if(second != 0) {
				result = first % second;
			}
			else{
				System.out.println("0으로 나눌 수 없습니다.");
				System.exit(0);
			}

		}
		else {
			System.out.println("잘못된 연산자 입력");
			System.exit(0);
		}

		System.out.printf("결과 : %d %s %d = %d", first,operator,second,result);
		sc.close();
	}
}
