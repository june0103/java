public class VariableMain01 {

	public static void main(String[] args) {
		
		//������ ���� ������ �� �ִ� �޸��� ����
		
		//���� ���� - ������� �޸� ������ �����Ű�� ��		
		int num;
		//���� �ʱ�ȭ
		num = 10; //=�� ������ ���� �ƴ� ����
		
		System.out.println(num);
		
		//���� ���� �� �ʱ�ȭ�� ���ÿ�
		int num1 = 15;
		
		System.out.println(num1);
		
		//������ ������ ����
		num = 20;
		System.out.println(num);
		
		//������ ���������δ� ���� ���� �� �� ����
		//int num = 11; x
		
		//������ �ڷ����� ����ϱ� ������ �ι�° ������ ���� �ڷ����� ��������
		int a= 10, b = 20;
		
		int result = a+b;
		
		System.out.println(result);
		System.out.printf("result = %d%n", result);
	
		System.out.println("result = "+result);
		/*
		 * +���� = ���� + ����
		 * +���� = ���ڿ� + ���� -> ���ο� ���ڿ�
		 * 		  ���� + ���ڿ�
		 * 		  ���ڿ� + ���ڿ�
		 */
		
		System.out.println("result = " + a + b); //���� ����� ���
		System.out.println("result = " + (a+b)); //���� �������� ���
		
		//���� ���� �� ��� �Ǵ� �����ϱ� ���� �ݵ�� �ʱ�ȭ �ʼ�
		/* 
		 * int no;
		 * System.out.println(no);
		*/
	}

}
