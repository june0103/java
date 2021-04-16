public class CastMain01 {

	public static void main(String [] args) {
		
		System.out.println("===묵시적 형변화(자동 형변환)===");
		//더 큰 자료형으로 승격이 일어나는 형태,
		//정보의 손실이 전혀 없으며 자동적으로 발생한다.
		
		byte b1 = 127;
		byte b2 = 127;		
		//32bit 미만 byte형 데이터를 연산을 하면 자동으로
		//자료형이 int로 변환된다.
		int b3 = b1 + b2;
		System.out.println("b3 = "+b3);
		
		short s1 = 3275;
		short s2 = 3456;
		//32bt 미만 short형 데이터를 연산을 하면 자동으로
		//자료형이 int로 변환됨
		int s3 = s1 + s2;
		System.out.println("s3 = "+s3);
		
		int in1 = 12345;
		long lg1 = 12345L;
		long result = in1 + lg1; //int -> long 자동형변환
		System.out.println("result = "+result);
		
		int in2 = 35;
		double db1 = 5.8;
		double result2 = in2 + db1; //int -> double 자동형변환
		System.out.println("result2 = "+result2);
		
		
	}
}
