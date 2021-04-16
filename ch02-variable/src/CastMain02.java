public class CastMain02 {

	public static void main(String[] args) {

		System.out.println("===명시적 형변환(강제 형변환)===");
		//더 작은 자료형으로 강등이 일어나는 형태
		//정보의 손실이 발생할 수 있다.
		
		byte b1 = 127;
		byte b2 = 127;
		
		//자동적으로 int로 형변환 되는것을 byte로 강제 형변환 시킨다.
		byte b3 = (byte)(b1 + b2);
		
		System.out.println("b3 = "+b3);
		
		
		short s1 = 32767;
		short s2 = 32767;
		
		//자동적으로 int로 형변환 되는것을 short로 강제 형변환 시킨다.
		short s3 = (short)(s1 + s2);
		
		System.out.println("s3 = "+s3);
		
		
		int in1 = 123;
		float f1 = 25.6f;
		int result = in1 + (int)(f1);
		
		//float -> int 강제 형변환(소수점 제거)
		System.out.println("result = " + result);
		
		
		int in2 = 56;
		long lg1 = 123L;
		
		//long -> int 강제 형변환
		int result2 = in2 + (int)(lg1);
		System.out.println("result2 = "+ result2);
		
	}

}
