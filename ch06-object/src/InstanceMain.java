public class InstanceMain {

	//��� �޼���
	public int sum(int a, int b){
		return a + b;
	}
	
	public static void main(String[] args) {
		//��ü ���� �� ����
		InstanceMain im = new InstanceMain();
		//��ü�� ��� �޼��� ȣ��
		int result = im.sum(10, 20);
		
		System.out.println(result);
		
	}
	
}
