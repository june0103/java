public class ContinueMain {

	public static void main(String[] args) {
		
		for(int i = 0; i<=10; i++) {
			
			//continue�� Ư�� ������ �� ���๮�� ������ ���߰� ���� �ݺ� ȸ���� �ǳʶ�
			
			if(i%3 == 0)
				continue;
			
			System.out.println(i);
		}
	}
}
