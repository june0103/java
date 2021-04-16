public class CastMain03 {

	public static void main(String[] args) {
		
		char chr = 'A';
		int in = 10;
		
		//char은 int로 자동형변환 된다.
		int result = in + chr;
		
		System.out.println("result = "+result);
		
		//int -> char로 명시적 형변환 된다.
		System.out.println("result = "+(char)result);
		
	}

}
