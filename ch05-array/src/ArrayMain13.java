public class ArrayMain13 {

	public static void main(String[] args) {

		// 2���� �迭 ����, ����, �ʱ�ȭ (2�� 4��)
		int array[][] = { { 10, 20, 30, 40 }, { 50, 60, 70, 80 } };

		// 2���� �迭 ���
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println("array[" + i + "][" + j + "] : " + array[i][j]);
			}
		}

	}
}
