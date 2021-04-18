public class IfMain03 {

	public static void main(String [] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int a = input.nextInt();
		
		if(a > 0) { //양수
			
			System.out.println(a + "는 양수입니다.");
						
		}
		else if(a<0) {	//음수
			
			System.out.println(a + "는 음수입니다.");
			
		}
		else {	//0
			
			System.out.println("0 입니다.");
		
		}
		
		
		input.close();
	
	}
}
