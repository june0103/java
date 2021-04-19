package DungeonStriker;

public class Monster extends Unit {
	
	

	public Monster(String Monster_name, int Monster_HP, int Monster_Atk, int Monster_Att, int Monster_Exp)
	{   ///생성자
		super(Monster_name, Monster_HP, Monster_Atk, Monster_Att, Monster_Exp);	//super는 부모클래스를 가리킨다
	}
	
	@Override	
	public void attack(Unit Enemy)	//몬스터클래스로 만들어진 인스턴스가 적을 공격할때 부모클래스의 어택을 오버라이딩하여 속성효과 부여
	{
		
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
	
	
	
	
