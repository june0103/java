public class ArrayMain01 {

	public static void main(String[] args) {
		
		char[]	ch; //�迭 ����
		ch = new char[4]; //�迭 ����
		
		//�迭 �ʱ�ȭ,  �迭�� �ּҰ��� �ε������ϸ� 0������ �����Ѵ�
		ch[0] = 'J';
		ch[1] = 'a';
		ch[2] = 'v';
		ch[3] = 'a';
		
		//�迭�� ����� �����͸� ���
		System.out.println(ch[0]);;
		System.out.println(ch[1]);;
		System.out.println(ch[2]);;
		System.out.println(ch[3]);;
		
		//�ݺ����� �̿��Ͽ� �迭�� ��Ҹ� ���
		for(int i = 0; i<ch.length; i++) {
			System.out.println("ch["+i+"] : " + ch[i]);
		}
		
		//�迭�� ����� ������ ���ÿ�
		int it[] = new int[6];
		
		//�迭�� �����ÿ� �ʱ�ȭ�� ���� �ʾƵ� �⺻������ �ʱ�ȭ�� �Ǿ��ִ�.
		//�� �ڷ����� �⺻���� �⺻�ڷ����� �������� ����
		for(int i = 0; i<it.length; i++) {
			System.out.println("it["+i+"] : " + it[i]);
		}
		
		//�迭�� ����, ����(����� �迭 ����), �ʱ�ȭ ���ÿ�
		char ch2[] = new char[] {'J', 'a', 'v', 'A'};
		
		for(int i = 0; i<ch2.length; i++) {
			System.out.println("ch2["+i+"] : " + ch2[i]);
		}
		
		//�迭�� ����, ����(�Ͻ��� �迭 ����), �ʱ�ȭ ���ÿ�
		char ch3[] = {'��', '��'};
		for(int i = 0; i<ch3.length; i++) {
			System.out.println("ch3["+i+"] : " + ch3[i]);
		}
	}
}
