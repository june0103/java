package DungeonStriker;

public class Warrior extends Attacker {

	public Warrior(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp)
	{    ///���� ������
		super(name, HP, Atk, Grad, Level, Att, Exp);
	}
	
	@Override	
	public void attack(Unit Enemy)	//�θ�Ŭ������ �����Լ��� �Ӽ�ȿ���߰�
	{
		System.out.println(this.name + "�� ������ ȿ���� ���ݷ� 10% ����!!");
		if( Att == 0 && Enemy.Att == 1)	 
		{
			System.out.println(this.name + " : Ǯ     " +Enemy.name + " : ��");
			System.out.println(this.name + " �� �Ӽ�ȿ�� ���ݷ� 10% ����!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 0 && Enemy.Att == 3)
		{
			System.out.println(this.name + " : Ǯ     " +Enemy.name + " : ��");
			System.out.println(this.name + " �Ӽ�ȿ�� ���ݷ� 10% ����...");
			this.Atk = Atk - Atk/10;
		}
		else if( Att == 1 && Enemy.Att == 2)	
		{
			System.out.println(this.name + " : ��     " +Enemy.name + " : ��");
			System.out.println(this.name + " �� �Ӽ�ȿ�� ���ݷ� 10% ����!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 1 && Enemy.Att == 0)
		{
			System.out.println(this.name + " : ��     " +Enemy.name + " : Ǯ");
			System.out.println(this.name + " �Ӽ�ȿ�� ���ݷ� 10% ����...");
			this.Atk = Atk - Atk/10;
		}
		else if( Att == 2 && Enemy.Att == 3)	
		{
			System.out.println(this.name + " : ��     " +Enemy.name + " : Ǯ");
			System.out.println(this.name + " �� �Ӽ�ȿ�� ���ݷ� 10% ����!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 2 && Enemy.Att == 1)
		{
			System.out.println(this.name + " : ��     " +Enemy.name + " : ��");
			System.out.println(this.name + " �Ӽ�ȿ�� ���ݷ� 10% ����...");
			this.Atk = Atk - Atk/10;
		}
		else if( Att == 3 && Enemy.Att == 0)	
		{
			System.out.println(this.name + " : ��     " +Enemy.name + " : Ǯ");
			System.out.println(this.name + " �� �Ӽ�ȿ�� ���ݷ� 10% ����!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 3 && Enemy.Att == 2)
		{
			System.out.println(this.name + " : ��     " +Enemy.name + " : ��");
			System.out.println(this.name + " �Ӽ�ȿ�� ���ݷ� 10% ����...");
			this.Atk = Atk - Atk/10;
		}

		
		super.attack(Enemy);
		
	}
}
