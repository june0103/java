public class ForMain02 {

	public static void main(String[] args) {
			
		//for 초기식; 조건식; 증감식
		for(int i=5; i>=1; i--) {
			System.out.print(i + "\t");
		}		
		System.out.println("\n-----------");
		
		for(int i=0; i<=10; i+=2) {
			System.out.print(i + "\t");
		}		
		System.out.println("\n-----------");
	
		for(int i=0; i<=10; i++) {
			
			if(i%2 == 0) { //짝수일때만 출력
				System.out.print(i + "\t");	
			}
			
		}		
		System.out.println("\n-----------");
	}
}
