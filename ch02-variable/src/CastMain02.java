public class CastMain02 {

	public static void main(String[] args) {

		System.out.println("===����� ����ȯ(���� ����ȯ)===");
		//�� ���� �ڷ������� ������ �Ͼ�� ����
		//������ �ս��� �߻��� �� �ִ�.
		
		byte b1 = 127;
		byte b2 = 127;
		
		//�ڵ������� int�� ����ȯ �Ǵ°��� byte�� ���� ����ȯ ��Ų��.
		byte b3 = (byte)(b1 + b2);
		
		System.out.println("b3 = "+b3);
		
		
		short s1 = 32767;
		short s2 = 32767;
		
		//�ڵ������� int�� ����ȯ �Ǵ°��� short�� ���� ����ȯ ��Ų��.
		short s3 = (short)(s1 + s2);
		
		System.out.println("s3 = "+s3);
		
		
		int in1 = 123;
		float f1 = 25.6f;
		int result = in1 + (int)(f1);
		
		//float -> int ���� ����ȯ(�Ҽ��� ����)
		System.out.println("result = " + result);
		
		
		int in2 = 56;
		long lg1 = 123L;
		
		//long -> int ���� ����ȯ
		int result2 = in2 + (int)(lg1);
		System.out.println("result2 = "+ result2);
		
	}

}
