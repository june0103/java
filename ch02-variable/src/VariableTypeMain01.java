public class VariableTypeMain01 {

	public static void main(String[] args) {
		
		System.out.println("=====����=====");
		
		boolean b = true;
		
		System.out.println("b = "+b);
		
		System.out.println("=====������=====");
		//������, ũ�� : 2byte, ǥ������ : 0 ~ 65,535
		//�ٱ��� ó���ϱ� ���� �����ڵ� ����� ���
		
		char c1 = 'A'; //���������� �ƽ�Ű�ڵ� �� 65
		
		System.out.println(c1);
		
		char c2 = 65; //A�� �ش��ϴ� �ƽ�Ű�ڵ� ��
		
		System.out.println(c2);
		
		char c3 = '\u0041'; //A�� �ش��ϴ� �����ڵ� �� 
		
		System.out.println(c3);
		
		char c4 = '��';
		
		System.out.println(c4);
		
		char c5 = '\uc790'; //�� �� �ش��ϴ� �����ڵ� ��
		
		System.out.println(c5);
		
	}
}
