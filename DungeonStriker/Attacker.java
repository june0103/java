package DungeonStriker;

public class Attacker extends Unit {

	
	
	public Attacker(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp)
	{    ///������, ����� ������
		super(name, HP, Atk, Grad, Level, Att, Exp);	//�θ�Ŭ������ ������ �Ķ���� ���� ��� 
							
	}
	
	
	@Override	
	public void attack(Unit Enemy)	//�θ�Ŭ������ Unit�� attack �������̵�
	{
		this.Atk = Atk + Atk/10; 	//���������� �нú� ���ݷ� ����
		super.attack(Enemy);		//�θ�Ŭ�������ִ� attack �޼ҵ带 �״�� �����Ѵ�.
	}
	
	
}

