public class SwitchMain03 {

	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int score;
		char grade;
		
		System.out.print("���� �Է�>");
		score = input.nextInt();
		
		if(score < 0 || score >100) {
			
			System.out.println("�߸� �Է��߽��ϴ�.");
			//���α׷� ����
			System.exit(0);
		}
		switch(score/10) { //0~10 ������ ������ش�
		
		case 10: //10�� 9�� ����� ���⿡ 19�� �ڵ�� �����ص� 9������Ǿ� ����� ����
				//���α׷� ���̴� ���
		case 9:
			grade = 'A';			
			break;
		case 8:
			grade = 'B';			
			break;
		case 7:
			grade = 'C';			
			break;
		case 6:
			grade = 'D';			
			break;
		default:
			grade = 'F';
			
		}
		
		System.out.printf("���� : %d%n", score);
		System.out.printf("��� : %c%n", grade);
		
		
		input.close();
		
	}
}