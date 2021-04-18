public class IfMain05 {

	public static void main(String[] args) {
		
		//비교연산자를 통하여 자동형변환 되는 상황을 조건문으로 알아본다.
		
		int a = 10;
		float b = 10.0f;
		
		if(a == b) { //int -> float, 10.0f == 10.0f int가 float로 자동 형변환
			
			System.out.println("10과 10.0f는 같다");
			
		}
		
		char c = '0';
		int d = 0;
		
		if(c != d) { //char -> int, 48 != 0 char이 아스키코드값 48 int로 자동 형변환
			
			System.out.println("'0'과 0은 같지 않다.");
						
		}
		
		char e = 'A';
		int f = 65;
		
		if(e == f) { //char -> int, 65 == 65 char이 아스키코드값 65 int로 자동 형변환
			
			System.out.println("'A'는 65와 같다.");
			
		}
		
	}
}
