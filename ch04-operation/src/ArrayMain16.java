public class ArrayMain16 {

	public static void main(String[] args) {
		
		int array1[] = {11, 22, 33, 44, 55};
		int array2[] = new int[10];
		
		//�迭 ����  
		//(����, ���� �迭�� ������ġ, ����Ǵ� ��ġ(����ó), ����ó ���� ������ġ, ����Ǵ� �迭�� ����� ��)
		System.arraycopy(array1, 0, array2, 3, 5);
		
		System.out.println("���� �迭");
		for(int i : array1) {
			System.out.print(i + " ");
			
		}
		System.out.println("\n���纻 �迭");
		for(int j : array2) {
			System.out.print(j + " ");
		}
		
		
	}
}
