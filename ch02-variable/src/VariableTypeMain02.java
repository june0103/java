public class VariableTypeMain02 {

	public static void main(String[] args) {

		System.out.println("=====������=====");
		
		//byte, ũ�� : 1byte, ǥ������ : -128 ~ 127
		byte b1 = 127;
		System.out.println("b1 = "+b1);
		
		//short, ũ�� : 2byte, ǥ������ : -32,768 ~ 32,767
		short s1 = 32767;
		System.out.println("s1 = "+s1);
		
		//int, ũ�� : 4byte, ǥ������ : -2,147,483,648 ~ 2,147,483,647
		//java�� ������ ǥ���� �� �⺻ ���
		int i1 = 2147483647;
		System.out.println("i1 = "+i1);
		
		//long, ũ�� : 8byte
		long l1 = 12345L;
		System.out.println("l1 = "+l1);
		
		System.out.println("=====�Ǽ���=====");
		
		//float, ũ�� : 4byte
		float f1 = 9.1f;
		System.out.println("f1 = "+f1);
		
		//double, ũ�� : 8byte
		//�Ǽ��� ǥ���� �� �⺻���
		double du = 9.8;
		System.out.println("du = "+du);
		
		System.out.println("=====���ڿ�=====");
		//���ڿ��� ǥ���� �� �����ڷ����� �����.
		String str = "Hello World!";
		System.out.println("str = "+str);
	}

}
