public class CastMain03 {

	public static void main(String[] args) {
		
		char chr = 'A';
		int in = 10;
		
		//char�� int�� �ڵ�����ȯ �ȴ�.
		int result = in + chr;
		
		System.out.println("result = "+result);
		
		//int -> char�� ����� ����ȯ �ȴ�.
		System.out.println("result = "+(char)result);
		
	}

}
