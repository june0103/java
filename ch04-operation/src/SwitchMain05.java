public class SwitchMain05 {

	public static void main(String[] args) {

		//����. 2���� ������ �Է�. ������(+,-,*,/,%) �Է�.����� ���
		/* ����
		 * ù��° �� : 10
		 * ������ : -
		 * �ι��� �� : 5
		 * ��� : 10 - 5 = 5
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int first, second, result = 0;
		String operator;
		
		System.out.print("ù��° �� : ");
		first = input.nextInt();
		System.out.print("������ : ");
		operator = input.next();
		System.out.print("�ι�° �� : ");
		second = input.nextInt();
		
		switch(operator) {
		case "+" : result = first + second; break;
		case "-" : result = first - second; break;
		case "*" : result = first * second; break;
		case "/" :
			switch(second) {
			case 0 : System.out.println("0���� ���� �� �����ϴ�."); System.exit(0);
			default :  result = first / second;
			}
			break;
		case "%" : 
			switch(second) {
			case 0 : System.out.println("0���� ���� �� �����ϴ�."); System.exit(0);
			default :  result = first % second;
			}
			break;
		default : System.out.println("�߸��� ������ �Է�");
		System.exit(0);
		}
		
		System.out.printf("��� : %d %s %d = %d", first,operator,second,result);
		input.close();
		
	}

}
