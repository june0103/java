package DungeonStriker;

public class Monster extends Unit {
	
	

	public Monster(String Monster_name, int Monster_HP, int Monster_Atk, int Monster_Att, int Monster_Exp)
	{   ///������
		super(Monster_name, Monster_HP, Monster_Atk, Monster_Att, Monster_Exp);	//super�� �θ�Ŭ������ ����Ų��
	}
	
	@Override	
	public void attack(Unit Enemy)	//����Ŭ������ ������� �ν��Ͻ��� ���� �����Ҷ� �θ�Ŭ������ ������ �������̵��Ͽ� �Ӽ�ȿ�� �ο�
	{
		
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
	
	
	
	
