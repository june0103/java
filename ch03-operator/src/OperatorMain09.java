public class OperatorMain09 {
	
	public static void main(String [] args) {
		
		System.out.println("=====조건 연산자(삼항연산자)=====");
		int x = 10;
		int y = -10;
		
		int X = x >= 0 ? x : -x;
		int Y = y >= 0 ? y : -y;
		//		 조건    참값: 거짓값
		
	
		System.out.println(X);
		System.out.println(Y);
	}

}
