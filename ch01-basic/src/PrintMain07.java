public class PrintMain07 {

	public static void main(String[] args) {
		
		/*
		System.out.printf("%s�� ������¸� �������ִ�. ���¿� "
				+ "%d���� ���ݵǾ� �ִµ� ���� %d���� �۱ݹ޾Ƽ� �ܰ�"
				+ " %,d���� �Ǿ���. Ư���������� %.1f�� ����Ǿ� ���� ����"
				+ " %.1f���� �޴´�.\n" ,"���缮",1000,325,1325,0.3,397.5 );
		*/
		
		System.out.println(" ");
		System.out.printf("%s : %s\n", "������","���缮");
		System.out.printf("%s : %,d��\n", "�۱ݵǱ��� �ܱ�",1000);
		System.out.printf("%s : %d��\n", "�۱ݵ� �ݾ�",325);
		System.out.printf("%s : %,d��\n", "�۱����� �ܱ�",1325);
		System.out.printf("%s : %.1f��\n", "���� �Աݵ� ����",397.5);
	}

}
