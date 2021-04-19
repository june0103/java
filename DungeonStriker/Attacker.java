package DungeonStriker;

public class Attacker extends Unit {

	
	
	public Attacker(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp)
	{    ///공격형, 방어형 생성자
		super(name, HP, Atk, Grad, Level, Att, Exp);	//부모클래스의 생성자 파라미터 변수 사용 
							
	}
	
	
	@Override	
	public void attack(Unit Enemy)	//부모클래스인 Unit의 attack 오버라이딩
	{
		this.Atk = Atk + Atk/10; 	//공격형유닛 패시브 공격력 증가
		super.attack(Enemy);		//부모클래스에있는 attack 메소드를 그대로 실행한다.
	}
	
	
}

