public class ArrayMain17 {

	public static void main(String[] args) {

		// ������ 5�� �Է¹޾� �迭�� �����ϰ�, ����� ���� �� �ִ밪�� �ּҰ��� ���ؼ� ���

		java.util.Scanner input = new java.util.Scanner(System.in);

		int array[] = new int[5];
		int max = 0, min = 0;

		for (int i = 0; i < array.length; i++) {

			System.out.print("���� �Է�> ");
			array[i] = input.nextInt();

		}

		System.out.println("===����� �迭===");
		for (int j : array) {
			System.out.print(j + " ");
		}

		java.util.Arrays.sort(array);

		System.out.println("\n===������ �迭===");
		for (int h : array) {
			System.out.print(h + " ");
		}

		min = array[0];
		max = array[array.length - 1];

		System.out.println("\n�ּҰ� > " + min);
		System.out.println("�ִ밪 > " + max);

		input.close();

	}
}
