public class IfMain08 {

	public static void main(String[] args) {
		
		//����. 2���� ������ �Է�. ������(+,-,*,/,%) �Է�.����� ���
		/* ����
		 * ù��° �� : 10
		 * ������ : -
		 * �ι��� �� : 5
		 * ��� : 10 - 5 = 5
		 */
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		int first, second, result = 0;
		String operator;
		
		System.out.print("ù��° �� : ");
		first = sc.nextInt();
		System.out.print("������ : ");
		operator = sc.next();
		System.out.print("�ι�° �� : ");
		second = sc.nextInt();
		
		if(operator.equals("+")){
			result = first + second;
		}
		else if(operator.equals("-")){
			result = first - second;
		}
		else if(operator.equals("*")){
			result = first * second;
		}
		else if(operator.equals("/")){
			if(second !=0 ) {
			result = first / second;
			}
			else {
				System.out.println("0���� ���� �� �����ϴ�.");
				System.exit(0);
			}
		}
		else if(operator.equals("%")){
			if(second != 0) {
				result = first % second;
			}
			else{
				System.out.println("0���� ���� �� �����ϴ�.");
				System.exit(0);
			}

		}
		else {
			System.out.println("�߸��� ������ �Է�");
			System.exit(0);
		}

		System.out.printf("��� : %d %s %d = %d", first,operator,second,result);
		sc.close();
	}
}
