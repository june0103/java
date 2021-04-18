public class ForMain04 {

	public static void main(String[] args) {
		
		int a;
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("구구단 입력>");
		a = input.nextInt();
		
		for(int i =1; i<=9; i++) {
			
			System.out.printf("%d X %d = %d%n",a,i,a*i);
			
		}
		
		input.close();
		
	}
}
