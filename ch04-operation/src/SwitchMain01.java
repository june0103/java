public class SwitchMain01 {

	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("정수 하나 입력>");
		int a = input.nextInt();
		
		switch(a) { //switch(인자값) 인자값은 long을 제외한 정수형과 char
		
		case 1:
			System.out.println("1입력");
			break; //switch 블럭을 빠져나가는 역할
			
		case 2:
			System.out.println("2입력");
			break;
		
		case 3:
			System.out.println("3입력");
			break;
			
		default:
			System.out.println("1,2,3이 아닌 숫자입력");
					
		}
		
		
		input.close();
		
	}
}
