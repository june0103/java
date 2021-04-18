public class IfMain04 {

	public static void main(String [] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
	
		int score; //己利
		char grade; //殿鞭
		
		System.out.print("己利 涝仿 : ");
		score = input.nextInt();
		
		if(score >= 90 && score <= 100) {
			
			grade = 'A';
			
		}
		else if(score >= 80 && score <90 ) {
			
			grade = 'B';
			
		}
		else if(score >= 70 && score <80 ) {
			
			grade = 'C';
			
		}
		else if(score >= 60 && score <70 ) {
			
			grade = 'D';
			
		}
		else if(score >= 0 && score <60 ) {
			
			grade = 'F';
			
		}
		else {
			
			grade = '?';
			
		}
		System.out.printf("己利 : %d%n", score);
		System.out.printf("殿鞭 : %c%n", grade);
		input.close();
		
	}
	
}
