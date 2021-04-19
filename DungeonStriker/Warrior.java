package DungeonStriker;

public class Warrior extends Attacker {

	public Warrior(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp)
	{    ///전사 생성자
		super(name, HP, Atk, Grad, Level, Att, Exp);
	}
	
	@Override	
	public void attack(Unit Enemy)	//부모클래스의 어택함수에 속성효과추가
	{
		System.out.println(this.name + "의 워리어 효과로 공격력 10% 증가!!");
		if( Att == 0 && Enemy.Att == 1)	 
		{
			System.out.println(this.name + " : 풀     " +Enemy.name + " : 땅");
			System.out.println(this.name + " 의 속성효과 공격력 10% 증가!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 0 && Enemy.Att == 3)
		{
			System.out.println(this.name + " : 풀     " +Enemy.name + " : 불");
			System.out.println(this.name + " 속성효과 공격력 10% 감소...");
			this.Atk = Atk - Atk/10;
		}
		else if( Att == 1 && Enemy.Att == 2)	
		{
			System.out.println(this.name + " : 땅     " +Enemy.name + " : 물");
			System.out.println(this.name + " 의 속성효과 공격력 10% 증가!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 1 && Enemy.Att == 0)
		{
			System.out.println(this.name + " : 땅     " +Enemy.name + " : 풀");
			System.out.println(this.name + " 속성효과 공격력 10% 감소...");
			this.Atk = Atk - Atk/10;
		}
		else if( Att == 2 && Enemy.Att == 3)	
		{
			System.out.println(this.name + " : 물     " +Enemy.name + " : 풀");
			System.out.println(this.name + " 의 속성효과 공격력 10% 증가!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 2 && Enemy.Att == 1)
		{
			System.out.println(this.name + " : 물     " +Enemy.name + " : 땅");
			System.out.println(this.name + " 속성효과 공격력 10% 감소...");
			this.Atk = Atk - Atk/10;
		}
		else if( Att == 3 && Enemy.Att == 0)	
		{
			System.out.println(this.name + " : 불     " +Enemy.name + " : 풀");
			System.out.println(this.name + " 의 속성효과 공격력 10% 증가!! ");
			this.Atk = Atk + Atk/10; 
		}
		else if( Att == 3 && Enemy.Att == 2)
		{
			System.out.println(this.name + " : 불     " +Enemy.name + " : 물");
			System.out.println(this.name + " 속성효과 공격력 10% 감소...");
			this.Atk = Atk - Atk/10;
		}

		
		super.attack(Enemy);
		
	}
}
