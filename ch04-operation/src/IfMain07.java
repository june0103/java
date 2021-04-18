public class IfMain07 {

	public static void main(String[] args) {
		
		//두개의 정수를 입력받아 변수에 저장하고 두값 중 최대값, 최소값을 구해라 
		//입력한 두 수가 같을경우 "두 수는 같다"
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		int a, b, max, min;
		
		System.out.print("첫번째 정수 : ");
		a = sc.nextInt();
		System.out.print("두번째 정수 : ");
		b = sc.nextInt();
				
		if(a>b) {
			
			max = a;
			min = b;
			System.out.println("max : " + max);
			System.out.println("min : " + min);
		}
		else if(a<b) {
			
			max = b;
			min = a;
			System.out.println("max : " + max);
			System.out.println("min : " + min);
			
		}
		else {
			
			System.out.println("두 수는 같다");
			
		}
				
		sc.close();
			
	}
	
}
