public class ForMain06 {

	public static void main(String[] args) {

		/*�ǽ�
		 * for���� �̿��ؼ� 1���� 100������ ���� �߿���
		 * 3�� ����� ������ ���ϰ� ����Ͻÿ�.
		 */
		
		int num=0;
		
		for(int i=1; i<=100; i++) {
			
			if(i%3 == 0)
			num += i;
			
		}
		
		System.out.println(num);
		
		
	}

}
