package DungeonStriker;

public class Map{
	
	
	public void Map_Select() //���θ޴����� �������ý� ������ ���ù���
	{
		System.out.println("�� �������� �Ӽ����Ͱ� �����մϴ�.");
		
		System.out.println("--------------������ ������ �ּ���-------------");
		System.out.println("1.Ǯ����   2.������   3.������   4.������   5.����");
		System.out.println("---------------------------------------------");
	}
	
	
	public void Map_G()	//Ǯ���� ���ý� ������������ ����
	{
		System.out.println("���Ͱ� �ڷΰ����� �������ϴ�.");
		System.out.println("-----------------------------���������� ������ �ּ���-------------------------------");
		System.out.println("1.Ǯ-1  2.Ǯ-2  3.Ǯ-3  4.Ǯ-4  5.Ǯ-5  6.Ǯ-6  7.Ǯ-7  8.Ǯ-8  9.Ǯ-9  10.Ǯ-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	public void Map_L() //������ �����������ù���
	{
		System.out.println("���Ͱ� �ڷΰ����� �������ϴ�.");
		System.out.println("-----------------------------���������� ������ �ּ���-------------------------------");
		System.out.println("1.��-1  2.��-2  3.��-3  4.��-4  5.��-5  6.��-6  7.��-7  8.��-8  9.��-9  10.��-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public void Map_W()	//������ �����������ù���
	{
		System.out.println("���Ͱ� �ڷΰ����� �������ϴ�.");
		System.out.println("-----------------------------���������� ������ �ּ���-------------------------------");
		System.out.println("1.��-1  2.��-2  3.��-3  4.��-4  5.��-5  6.��-6  7.��-7  8.��-8  9.��-9  10.��-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	public void Map_F()	//Ǯ���� �����������ù���
	{
		System.out.println("���Ͱ� �ڷΰ����� �������ϴ�.");
		System.out.println("-----------------------------���������� ������ �ּ���-------------------------------");
		System.out.println("1.��-1  2.��-2  3.��-3  4.��-4  5.��-5  6.��-6  7.��-7  8.��-8  9.��-9  10.��-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	static Monster G1 = new Monster("�Ϲݿ�ũ", 100, 20, 0, 150);
	static Monster G2 = new Monster("�Ϲݰ��", 150, 21, 0, 160);
	static Monster G3 = new Monster("�Ϲ�Ʈ��", 200, 22, 0, 170);
	static Monster G4 = new Monster("�Ϲݾ𵥵�", 250, 23, 0, 180);
	static Monster G5 = new Monster("�Ϲݿ����", 300, 24, 0, 190);
	static Monster G6 = new Monster("������ũ", 350, 25, 0, 200);
	static Monster G7 = new Monster("�������", 400, 26, 0, 210);
	static Monster G8 = new Monster("����Ʈ��", 450, 27, 0, 220);
	static Monster G9 = new Monster("�����𵥵�", 500, 28, 0, 230);
	static Monster G10 = new Monster("���������", 550, 29, 0, 240);
	
	static Monster L1 = new Monster("�Ϲݿ�ũ", 100, 20, 1, 150);
	static Monster L2 = new Monster("�Ϲݰ��", 150, 21, 1, 160);
	static Monster L3 = new Monster("�Ϲ�Ʈ��", 200, 22, 1, 170);
	static Monster L4 = new Monster("�Ϲݾ𵥵�", 250, 23, 1, 180);
	static Monster L5 = new Monster("�Ϲݿ����", 300, 24, 1, 190);
	static Monster L6 = new Monster("������ũ", 350, 25, 1, 200);
	static Monster L7 = new Monster("�������", 400, 26, 1, 210);
	static Monster L8 = new Monster("����Ʈ��", 450, 27, 1, 220);
	static Monster L9 = new Monster("�����𵥵�", 500, 28, 1, 230);
	static Monster L10 = new Monster("���������", 550, 29, 1, 240);
	
	static Monster W1 = new Monster("�Ϲݿ�ũ", 100, 20, 2, 150);
	static Monster W2 = new Monster("�Ϲݰ��", 150, 21, 2, 160);
	static Monster W3 = new Monster("�Ϲ�Ʈ��", 200, 22, 2, 170);
	static Monster W4 = new Monster("�Ϲݾ𵥵�", 250, 23, 2, 180);
	static Monster W5 = new Monster("�Ϲݿ����", 300, 24, 2, 190);
	static Monster W6 = new Monster("������ũ", 350, 25, 2, 200);
	static Monster W7 = new Monster("�������", 400, 26, 2, 210);
	static Monster W8 = new Monster("����Ʈ��", 450, 27, 2, 220);
	static Monster W9 = new Monster("�����𵥵�", 500, 28, 2, 230);
	static Monster W10 = new Monster("���������", 550, 29, 2, 240);
	
	static Monster F1 = new Monster("�Ϲݿ�ũ", 100, 20, 3, 150);
	static Monster F2 = new Monster("�Ϲݰ��", 150, 21, 3, 160);
	static Monster F3 = new Monster("�Ϲ�Ʈ��", 200, 22, 3, 170);
	static Monster F4 = new Monster("�Ϲݾ𵥵�", 250, 23, 3, 180);
	static Monster F5 = new Monster("�Ϲݿ����", 300, 24, 3, 190);
	static Monster F6 = new Monster("������ũ", 350, 25, 3, 200);
	static Monster F7 = new Monster("�������", 400, 26, 3, 210);
	static Monster F8 = new Monster("����Ʈ��", 450, 27, 3, 220);
	static Monster F9 = new Monster("�����𵥵�", 500, 28, 3, 230);
	static Monster F10 = new Monster("���������", 550, 29, 3, 240);
	
	static Monster Boss = new Monster("Boss",10,200, 0, 300);
}
