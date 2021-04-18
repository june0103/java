public class IfMain06 {

	public static void main(String[] args) {
		
		//정수를 입력받아 짝수는 10, 홀수면 20을 더하여 출력
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int number, result;
		
		System.out.print("정수를 입력하세요 :");
		number = input.nextInt();
		
		if(number%2 == 0) {
			
			result = number + 10;
		}
		else {
			
			result = number + 20;
			
		}
		
		System.out.println("입력한 정수 : "+ number);
		System.out.println("결과값 : " + result);
		
		input.close();
		
	}
}
