public class IfMain05 {

	public static void main(String[] args) {
		
		//�񱳿����ڸ� ���Ͽ� �ڵ�����ȯ �Ǵ� ��Ȳ�� ���ǹ����� �˾ƺ���.
		
		int a = 10;
		float b = 10.0f;
		
		if(a == b) { //int -> float, 10.0f == 10.0f int�� float�� �ڵ� ����ȯ
			
			System.out.println("10�� 10.0f�� ����");
			
		}
		
		char c = '0';
		int d = 0;
		
		if(c != d) { //char -> int, 48 != 0 char�� �ƽ�Ű�ڵ尪 48 int�� �ڵ� ����ȯ
			
			System.out.println("'0'�� 0�� ���� �ʴ�.");
						
		}
		
		char e = 'A';
		int f = 65;
		
		if(e == f) { //char -> int, 65 == 65 char�� �ƽ�Ű�ڵ尪 65 int�� �ڵ� ����ȯ
			
			System.out.println("'A'�� 65�� ����.");
			
		}
		
	}
}
