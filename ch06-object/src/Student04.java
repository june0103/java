public class Student04 {
	//�������, ��ü�� ������ ��ü�� ���Եȴ�
		String name;
		int korean;
		int math;
		int english;
		
		//����޼���, �̹� ����� ��������� ���ڷ� �����ʰ� ���� �޾Ƽ� �۾��� �����ϴ�
		//���� ���ϴ� �޼���
		public int makeSum() {
			
			return korean + english + math;	
		}
		
		//����޼���, ����� �޼��带 ���� ������ ����� �����ϴ�
		//��� ���ϴ� �޼���
		public int makeAverage() {
			
			return makeSum() / 3;
		}
		
		
		public static void main(String[] args) {
			//��ü ���� �� ����
			Student04 s = new Student04();
			
			s.name = "ȫ�浿";
			s.korean = 98;
			s.english = 97;
			s.math = 96;
						
			System.out.println("�̸� : " + s.name);
			System.out.println("���� : " + s.korean);
			System.out.println("���� : " + s.english);
			System.out.println("���� : " + s.math);
			System.out.println("���� : " + s.makeSum());
			System.out.println("��� : " + s.makeAverage());
		}
}
