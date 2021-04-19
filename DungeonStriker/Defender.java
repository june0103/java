package DungeonStriker;

public class Defender extends Tanker {	//1차클래스 탱커를 상속받는 2차클래스 디펜더
	
	public Defender(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp)
	{    
		super(name, HP, Atk, Grad, Level, Att, Exp);
	}
	
	@Override	
	public void attack(Unit Enemy)	//부모클래스의 attack 메소드 오버라이딩
	{
		
		if( Att == 0 && Enemy.Att == 1)	 // 자기자신의 속성이 풀이고 적의속성이 땅일때 속성효과
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

		
		super.attack(Enemy);	//부모클래스에있는 attack 메소드를 그대로 실행한다.
		
		
		
	}
	

}
