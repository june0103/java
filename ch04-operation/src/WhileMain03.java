public class WhileMain03 {

	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int a = 0, total = 0;
		System.out.println("0 전까지 입력받은 정수로 합 구하기");
		
		while(true) {
			
			System.out.print("누적 정수를 입력 : ");
			a = input.nextInt();
			
			if(a!=0) { //누적
				
				total += a;
				System.out.println("누적 = "+total);
				System.out.println("--------------");
				
			}
			else { //a가 0 일때
				
				System.out.println("total = "+total);
				
				input.close();
				System.exit(0);
			}
				
			
			
		}
		
		
	}
}
