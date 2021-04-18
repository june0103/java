public class ArrayMain10 {

	public static void main(String[] args) {

		// 10,20,30,40,50�� �ʱⰪ���� ���� 1���� �迭�� test�̸����� ����,����,�ʱ�ȭ
		// �ݺ����� �̿��Ͽ� test �迭�� ��Ҹ� ���
		// Ȯ��for���� �̿��Ͽ� ���
		// �ε��� 3�� �����͸� 100���� ����
		// ������ ����� ���� 200���� ����
		// �ݺ����� �̿��Ͽ� ��� �ε����� ���� 0���� �ʱ�ȭ
		// Ȧ�� �ε����� 10, ¦�� �ε����� 20���� �ʱ�ȭ
		// ��� �ε����� ���հ� ����� ���ϰ� ���

		// �Ͻ��� �迭����
		int test[] = { 10, 20, 30, 40, 50 };

		// for�ݺ����� �̿��Ͽ� �迭 ���
		for (int i = 0; i < test.length; i++) {

			System.out.println(test[i]);

		}

		System.out.println("--------------");

		// Ȯ��for���� �̿��Ͽ� �迭 ���
		for (int i : test) {

			System.out.println(i);

		}

		System.out.println("--------------");

		// �ε���3�� �����͸� 100���� ����
		test[3] = 100;
		System.out.println(test[3]);

		System.out.println("--------------");

		// ������ �ε����� �����͸� 200���� ����
		// test[4] = 200;
		test[test.length - 1] = 200;
		System.out.println(test[4]);

		System.out.println("--------------");
		for (int i : test) {

			System.out.println(i);

		}

		// �ݺ����� �̿��Ͽ� ��� �ε����� ���� 0���� �ʱ�ȭ
		for (int i = 0; i < test.length; i++) {

			test[i] = 0;
			System.out.println(test[i]);

		}
		System.out.println("--------------");

		// Ȧ�� �ε����� 10, ¦�� �ε����� 20���� �ʱ�ȭ
		for (int i = 0; i < test.length; i++) {

			if (i % 2 == 0) {
				test[i] = 20;
			} else {
				test[i] = 10;
			}

		}
		System.out.println("--------------");
		for (int i : test) {

			System.out.println(i);

		}

		// ��� �ε����� ���հ� ����� ���ϰ� ���
		int sum = 0, avg = 0;
		for (int i = 0; i < test.length; i++) {

			sum += test[i];
			avg = sum / test.length;

		}
		System.out.println("--------------");
		System.out.println("sum = " + sum);
		System.out.println("avg = " + avg);

	}
}
