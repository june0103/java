public class ArrayMain11 {

	public static void main(String[] args) {

		// ������. ���� �Է��Ͽ� �迭�� ������ �� ���

		java.util.Scanner input = new java.util.Scanner(System.in);
		int array[] = new int[9];
		int dan;

		System.out.print("���� �Է� > ");
		dan = input.nextInt();

		for (int i = 0; i < array.length; i++) {

			array[i] = dan * (i + 1);
			System.out.println(dan + " X " + (i + 1) + "=" + array[i]);

		}

		input.close();
	}
}
