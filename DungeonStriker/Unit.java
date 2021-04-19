package DungeonStriker;

import java.util.Random;
import java.util.Scanner;

class Unit {
	
	String name;
	int HP;
	int Atk;
	int Grad;
	int Level;
	int Att; //Attribute
	int Exp;
	
	Scanner sc = new Scanner(System.in);

	static Unit[] UnitList = new Unit[100];	//유닛클래스 배열로 유닛을 뽑고 저장시키기위한 유닛 목록용도
	static Unit[] UnitList_battle = new Unit[3]; //던전파티 배열
	
	public Unit(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp) //이름,체력,공격력,등급,레벨,속성,경험치의 파라미터
	{  //공격형, 방어형 생성자
		this.name = name;	//this 는 현재클래스의 인스턴스를 가리킨다
		this.HP = HP;
		this.Atk = Atk;
		this.Grad = Grad;
		this.Level = Level;
		this.Att = Att;
		this.Exp = Exp;
		
	}
	
	public Unit(String Monster_name, int Monster_HP, int Monster_Atk, int Monster_Att, int Monster_Exp) //이름,체력,공격력,속성,경험치의 파라미터
	{   ///몬스터 생성자	
		this.name = Monster_name;
		this.HP = Monster_HP;
		this.Atk = Monster_Atk;
		this.Att = Monster_Att;
		this.Exp = Monster_Exp;
	}
	
	
	void incHP(int Point)  //체력증가 메소드 
	{ 
		this.HP += Point; //point만큼 체력이 증가
	}
	
	void decHP(int Point) //체력감소 메소드
	{ 
		this.HP -= Point; //point만큼 체력 감소
	}
	
	int getHP() //체력확인 메소드
	{
		return this.HP; //this가 가르키는 HP를 리턴
	}
	
	int getLevel() //레벨확인 메소드
	{ 
		return this.Level; 
	}

	void getInfo() //유닛정보확인 메소드
	{
		
		System.out.println("---------------------------------");
		System.out.println("이름 : " + this.name); 
		if(this.Att ==0) //속성확인
		{
			System.out.println("속성 : 풀");
		}
		else if(this.Att == 1)
		{
			System.out.println("속성 : 땅");
		}
		else if(this.Att == 2)
		{
			System.out.println("속성 : 물");
		}
		else if(this.Att ==3)
		{
			System.out.println("속성 : 불");
		}
		System.out.println("등급 : " + this.Grad);
		System.out.println("레벨 : " + this.Level);
		System.out.println("체력 : " + this.HP);
		System.out.println("공격력 : " + this.Atk);
		System.out.println("경험치 : " + this.Exp);
		System.out.println("---------------------------------");
	}
	
	void getMInfo() //몬스터 정보확인 메소드
	{
		System.out.println("몬스터 등장!!");
		System.out.println("-------------------------------------");
		System.out.println("이름 : " + this.name);
		if(this.Att ==0)
		{
			System.out.println("속성 : 풀");
		}
		else if(this.Att == 1)
		{
			System.out.println("속성 : 땅");
		}
		else if(this.Att == 2)
		{
			System.out.println("속성 : 물");
		}
		else if(this.Att ==3)
		{
			System.out.println("속성 : 불");
		}
		System.out.println("체력 : " + this.HP);
		System.out.println("공격력 : " + this.Atk);
		System.out.println("-------------------------------------");
	}

	
	public void attack(Unit Enemy) 		//공격 메소드 (입력자료형 입력변수)
	{	
		
		
		String s1 = this.name + "의 공격력 : " + this.Atk+"\n"; //공격력 출력
		String s2 = this.name + " -> " + Enemy.name + " 공격!!\n";
		Runnable attack_bgm = new Attack_Thread(); //공격메소드실행시 공격bgm과 그림 쓰레드 실행하기위해  Runnable의 인스턴스를 Thread의 생성자로 전달
		Thread a_bgm = new Thread(attack_bgm);	//Thread 생성자
		try { //예외처리문
			Thread.sleep(500);	//sleep은 쓰레드의 흐름을 잠시 멈추고 실행시킨다. 1000 = 1초
		} catch (InterruptedException e) {
			
		}
		a_bgm.start();	//attack_bgm 쓰레드 시작
		try {
			a_bgm.join(); //join을사용하여 해당스레드가 종료되기까지 기다렸다가 다음진행
			}
		catch(InterruptedException e)
		{}
		for(int i=0; i<s1.length(); i++) //문자열 s1한글자씩출력
		{
			System.out.print(s1.charAt(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			
			}
		}
		for(int i=0; i<s2.length(); i++)
		{
			System.out.print(s2.charAt(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			
			}
		}
		if(Enemy instanceof Defender)	//공격메소드의 입력변수 Enemy가 디펜더의 인스턴스면
		{
			
			Enemy.decHP(this.Atk-this.Atk/10); 
			System.out.println(Enemy.name + "의 디펜더 효과로 받는피해 10% 감소!!");
		}
		
		else 	//공격메소드의 입력변수 Enemy의 피는 가르키는 인스턴스의 공격력만큼 감소
		{
			Enemy.decHP(this.Atk);
		}
		
		
		String s3 = Enemy.name + "의 남은 HP : " + Enemy.getHP()+"\n";
		for(int i=0; i<s3.length(); i++)
		{
			System.out.print(s3.charAt(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}
		
		
	}
	
	

	public boolean Live() //생존여부 메소드
	{
		
		if(HP <= 0) { 	//HP가 0이하이면 거짓의 값을 받는다
			return false;
		}
		
		else {
			return true;
		}
	}
	
	static void Grad_up() throws InterruptedException //등급강화메소드
	{
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		boolean Grad_up_flag = true;	//등급강화 반복 플래그
		
		while(Grad_up_flag)
		{
			
			if(Unit.UnitList[0]==null) //보유 유닛이 0명일때
			{
				System.out.println("현재 보유하고있는 유닛이 없습니다.");
				Grad_up_flag = false;
			}
			
			else
			{
				System.out.println("---------------------------------");
				System.out.println("1.등급 강화(5000원)       2.나가기");
				System.out.println("---------------------------------");
				int Grad_menu = sc.nextInt();
				
				if(Grad_menu ==1) //등급강화
				{
					if(User.Money>=5000)	//소유금액이 5천원보다 많을때 등급강화시작
					{
						System.out.println("----------보유 유닛 목록----------");
						
						for(int a=0; a<Shop.Unit_No; a++)		//보유유닛목록 출력 반복문
						{
							System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
						}
						System.out.println("등급 강화 할 유닛을 선택해주세요. ");
						int Grad_Unit_Select = sc.nextInt();				
						
						System.out.println("강화 전 유닛 정보");
						Unit.UnitList[Grad_Unit_Select-1].getInfo();	//유닛리스트에서 선택한 유닛의 정보확인메소드 사용
						Thread.sleep(2000);
						
						int plus_Atk = random.nextInt(11);	//랜덤을사용하여 능력치 강화
						int plus_HP = random.nextInt(101);
						System.out.println("강화 성공!!");
						System.out.println("추가 HP : " + plus_HP);
						System.out.println("추가 Atk : " + plus_Atk);
						Unit.UnitList[Grad_Unit_Select-1].Grad += 1;	//유닛리스트에서 선택한 인덱스의 등급 +1
						Unit.UnitList[Grad_Unit_Select-1].HP += plus_HP;	//HP +
						Unit.UnitList[Grad_Unit_Select-1].Atk += plus_Atk;	//Atk +
						
						System.out.println("강화 후 유닛정보 ");
						Unit.UnitList[Grad_Unit_Select-1].getInfo();		//능력치강화후 정보보기
						User.Money-=5000;
						System.out.println("소지금 : "+User.Money);
						System.out.println("나가시려면 아무키나 입력해주세요.");
						try		//아무키나 입력
				        {
				            System.in.read();	//콘솔창에서 입력을받는 함수 (아스키코드)
				        }  
				        catch(Exception e)
				        {}  
					}
					else		//소유금액이 적을때 
					{
						System.out.println("금액이 부족합니다.");
					}
				}// 등급강화 종료
				
				else if(Grad_menu == 2) //나가기
				{
					Grad_up_flag = false;
				}
				
				else	//잘못된입력
				{
					System.out.println("잘못된 입력입니다.");
				}
			
			}
			
		}
		
		
	}
	
	public static void Battle_G1() throws InterruptedException	//스테이지선택 후 배틀메소드
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();	//던전 bgm Runnable
		Thread D_bgm = new Thread(Dungeon_BGM);		 
		D_bgm.start();							//던전bgm재생
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;		//배틀에서 행동선택
		int Battle_USelect;				//공격할 유닛선택
		int Per_Potion;					//포션획득확률 변수
		int Reset_M_HP, Reset_M_Atk;	//전투시작전 몬스터의 체력, 공격력 백업
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;	//전투시작전 유닛의 체력백업
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;	//전투시작전 유닛의 공격력 백업
		int Use_Select_Potion;	//포션사용할때 선택변수
		int Use_sPo;	//소형포션 사용갯수
		int Use_mPo;	//중형포션 사용갯수
		int Use_bPo;	//대형포셔 사용갯수
		char Exit;		//던전나가기 문자
		Menu menu = new Menu();	//메뉴클래스 객체와하여 사용
		
		
		System.out.println("풀 - 01 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G1.getMInfo(); //몬스터 G1에대한 정보 호출
		
		Reset_M_HP = Map.G1.HP;							//전투시작전 몬스터와 유닛의 체력과 공격력 백업
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G1.Live()) //몬스터와 유닛이 한마리라도 살아있을시 반복
		{
			
			menu.Battle_Select();	//배틀메뉴 호출
			Battle_Select_Action = sc.nextInt();	//배틀베뉴 핵션선택
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G1);	//선택한 유닛 G1공격
					Thread.sleep(1000);
					 if(!Map.G1.Live()) 	//몬스터 G1이 죽었다면 빠져나가고, 살아있을시 몬스터 G1이 유닛 공격
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때 아래조건 같음
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G1.Atk = Reset_M_Atk;	//공격종료 후 백업해놓은 몬스터와 유닛의 공격력 원상복구
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20); //체력증가 메소드를 사용하여 20만큼 회복


						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					
					break Loop1; //던전종료
				}
				
				else
					
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G1.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G1.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G1.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300) //유닛의 경험치가 레벨업조건에 만족하면
           {
        	   Unit.UnitList_battle[0].Level += 1;								//레벨업
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));	//다음레벨까지 필요경험치
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G1.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G1.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");	//던전종료후 골드획득
           User.Money += Reset_M_HP;		//돈획득		
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G1.HP = Reset_M_HP;	//던정종료시 몬스터와 유닛의 체력 원상복구
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();	//던전 bgm스레드 종료
	}
	
	public static void Battle_G2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 02 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G2.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G2.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
					Thread.sleep(1000);
					 if(!Map.G2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G2.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G2.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G2.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G2.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G2.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G3() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 03 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G3.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G3.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
					Thread.sleep(1000);
					 if(!Map.G3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G3.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G3.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G3.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G3.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G3.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G4() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 04 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G4.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G4.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
					Thread.sleep(1000);
					 if(!Map.G4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G4.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G4.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G4.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G4.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G4.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 05 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G5.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G5.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
					Thread.sleep(1000);
					 if(!Map.G5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G5.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G5.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G5.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G5.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G5.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 06 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G6.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G6.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
					Thread.sleep(1000);
					 if(!Map.G6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G6.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G6.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G6.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G6.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G6.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 07 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G7.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G7.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
					Thread.sleep(1000);
					 if(!Map.G7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G7.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G7.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G7.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G7.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G7.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 08 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G8.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G8.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
					Thread.sleep(1000);
					 if(!Map.G8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G8.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G8.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G8.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G8.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G8.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 09 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G9.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G9.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
					Thread.sleep(1000);
					 if(!Map.G9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G9.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G9.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G9.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G9.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G9.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("풀 - 10 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G10.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G10.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
					Thread.sleep(1000);
					 if(!Map.G10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G10.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G10.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G10.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G10.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G10.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L1() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 01 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L1.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L1.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L1.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
					Thread.sleep(1000);
					 if(!Map.L1.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L1.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L1.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L1.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L1.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L1.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L1.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L1.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 02 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L2.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L2.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
					Thread.sleep(1000);
					 if(!Map.L2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L2.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L2.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L2.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L2.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L2.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L3() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 03 던전 입장 중.....");
		Thread.sleep(1000);
		Map.G3.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.G3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G3.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
					Thread.sleep(1000);
					 if(!Map.G3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.G3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.G3.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G3.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.G3.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.G3.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.G3.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.G3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L4() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 04 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L4.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L4.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
					Thread.sleep(1000);
					 if(!Map.L4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L4.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L4.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L4.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L4.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L4.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 05 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L5.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L5.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
					Thread.sleep(1000);
					 if(!Map.L5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L5.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L5.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L5.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L5.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L5.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 06 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L6.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L6.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
					Thread.sleep(1000);
					 if(!Map.L6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L6.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L6.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L6.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L6.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L6.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 07 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L7.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L7.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
					Thread.sleep(1000);
					 if(!Map.L7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L7.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L7.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L7.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L7.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L7.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 08 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L8.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L8.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
					Thread.sleep(1000);
					 if(!Map.L8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L8.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L8.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L8.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L8.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L8.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 09 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L9.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L9.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
					Thread.sleep(1000);
					 if(!Map.L9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L9.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L9.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L9.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L9.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L9.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("땅 - 10 던전 입장 중.....");
		Thread.sleep(1000);
		Map.L10.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.L10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L10.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
					Thread.sleep(1000);
					 if(!Map.L10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.L10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.L10.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L10.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.L10.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.L10.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.L10.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.L10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W1() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 01 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W1.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W1.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W1.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
					Thread.sleep(1000);
					 if(!Map.W1.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W1.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W1.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W1.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W1.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W1.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W1.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W1.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 02 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W2.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W2.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
					Thread.sleep(1000);
					 if(!Map.W2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W2.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W2.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W2.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W2.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W2.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W3() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 03 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W3.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W3.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
					Thread.sleep(1000);
					 if(!Map.W3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W3.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W3.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W3.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W3.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W3.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W4() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 04 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W4.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W4.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
					Thread.sleep(1000);
					 if(!Map.W4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W4.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W4.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W4.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W4.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W4.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 05 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W5.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W5.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
					Thread.sleep(1000);
					 if(!Map.W5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W5.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W5.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W5.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W5.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W5.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 06 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W6.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W6.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
					Thread.sleep(1000);
					 if(!Map.W6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W6.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W6.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W6.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W6.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W6.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 07 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W7.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W7.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
					Thread.sleep(1000);
					 if(!Map.W7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W7.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W7.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W7.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W7.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W7.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 08 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W8.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W8.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
					Thread.sleep(1000);
					 if(!Map.W8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W8.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W8.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W8.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W8.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W8.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 09 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W9.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W9.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
					Thread.sleep(1000);
					 if(!Map.W9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W9.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W9.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W9.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W9.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W9.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("물 - 10 던전 입장 중.....");
		Thread.sleep(1000);
		Map.W10.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.W10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W10.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
					Thread.sleep(1000);
					 if(!Map.W10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.W10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.W10.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W10.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.W10.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.W10.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.W10.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.W10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
 		D_bgm.stop();
		
	}
	
	public static void Battle_F1() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 01 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F1.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F1.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F1.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
					Thread.sleep(1000);
					 if(!Map.F1.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F1.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F1.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F1.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F1.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F1.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F1.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F1.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
 		D_bgm.stop();
		
	}
	
	public static void Battle_F2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 02 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F2.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F2.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
					Thread.sleep(1000);
					 if(!Map.F2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F2.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F2.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F2.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F2.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F2.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
 		D_bgm.stop();
		
	}
	
	public static void Battle_F3() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 03 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F3.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F3.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
					Thread.sleep(1000);
					 if(!Map.F3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F3.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F3.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F3.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F3.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F3.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_F4() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 04 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F4.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F4.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
					Thread.sleep(1000);
					 if(!Map.F4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F4.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F4.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F4.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F4.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F4.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_F5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 05 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F5.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F5.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
					Thread.sleep(1000);
					 if(!Map.F5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F5.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F5.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F5.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F5.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F5.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_F6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 06 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F6.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F6.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
					Thread.sleep(1000);
					 if(!Map.F6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F6.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F6.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F6.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F6.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F6.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 07 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F7.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F7.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
					Thread.sleep(1000);
					 if(!Map.F7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F7.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F7.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F7.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F7.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F7.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 08 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F8.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F8.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
					Thread.sleep(1000);
					 if(!Map.F8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F8.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F8.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F8.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F8.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F8.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 09 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F9.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F9.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
					Thread.sleep(1000);
					 if(!Map.F9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F9.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F9.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F9.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F9.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F9.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("불 - 10 던전 입장 중.....");
		Thread.sleep(1000);
		Map.F10.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.F10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F10.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
					Thread.sleep(1000);
					 if(!Map.F10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.F10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.F10.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F10.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.F10.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.F10.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.F10.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.F10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_Boss() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("보스 던전 입장 중.....");
		Thread.sleep(1000);
		Map.Boss.getMInfo(); //몬스터 정보
		
		Reset_M_HP = Map.Boss.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.Boss.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.Boss.Live()) //던전 종료조건
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //공격하기
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //유닛다 살아있을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  레벨 : "+ Unit.UnitList_battle[a].Level+"  공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
					Thread.sleep(1000);
					 if(!Map.Boss.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[2].Level+" 공격력 : "+ Unit.UnitList_battle[2].Atk+" 체력 : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //첫번째,두번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 2) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //첫번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" 레벨 : "+ Unit.UnitList_battle[1].Level+" 공격력 : "+ Unit.UnitList_battle[1].Atk+" 체력 : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다." );
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 1) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //두번째, 세번째 유닛 죽었을때
				{
					System.out.println("-------------------------------------");
					System.out.println("파티 목록");
					System.out.println("1." +Unit.UnitList_battle[0].name +" 레벨 : "+ Unit.UnitList_battle[0].Level+" 공격력 : "+ Unit.UnitList_battle[0].Atk+" 체력 : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" 사망하여 공격할 수 없습니다.");
					System.out.println("-------------------------------------");
					
					System.out.println("공격할 유닛을 선택해주세요.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//죽은유닛 선택했을때
					{	
						System.out.println("선택하신 유닛은 사망하여 공격할 수 없습니다.");
						System.out.println("행동을 다시 선택해 주세요.");
						
					}
					
					else if(Battle_USelect == 0) 	//공격유닛  정상공격
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//그외 값 입력했을 시
					{
						System.out.println("잘못된 선택입니다.");
						System.out.println("행동을 다시 선택해 주세요.");
					}
				}
				
				Map.Boss.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //물약사용
				System.out.println("-----------물약 보유현황----------");
		   		System.out.println("1. 소형 물약(HP20) : "+User.Potion_small);
		   		System.out.println("2. 중형 물약(HP40) : "+User.Potion_medium);
		   		System.out.println("3. 대형 물약(HP60) : "+User.Potion_big);
				System.out.println("사용하실 물약을 선택하세요.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//소형물약선택
					if(User.Potion_small>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 20 회복!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 2: //중형물약 선택
					if(User.Potion_medium>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 40 회복!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
				case 3: //대형물약 선택
					if(User.Potion_big>0) //물약이 있을때
					{
						System.out.println("회복할 유닛을 선택하세요.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level+" 공격력 : "+ Unit.UnitList_battle[a].Atk+" 체력 : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[0].name + " 체력 : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[1].name + " 체력 : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //물약사용했을 때 최대체력 이상이 될경우 최대체력까지만 채워짐
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[2].name + " 체력 : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 60 회복!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " 체력 : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // 물약 없을 때
					{
						System.out.println("보유하고 있는 물약이 없습니다.");
					}
					break;
					
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break; //물약사용 종료
			case 3: //나가기
				System.out.println("던전을 나가시겠습니까? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //던전종료
				}
				
				else
					break;  //나가기 종료
			default : 
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
	
		//전투종료 후 결과 및 정산
		if(!Map.Boss.Live()) 		//플레이어가 이겼을경우
        {
		   System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------경험치획득------------");
          
           Unit.UnitList_battle[0].Exp +=Map.Boss.Exp;											//경험치증가
           System.out.println(Unit.UnitList_battle[0].name + " 경험치 " + Map.Boss.Exp +"획득!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.Boss.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " 경험치 " + Map.Boss.Exp +"획득!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.Boss.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " 경험치 " + Map.Boss.Exp +"획득!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " 레벨업!!");
        	   System.out.println("현재레벨 : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "레벨까지 필요경험치 : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------골드획득-------------");
           User.Money += Reset_M_HP;//돈획득
           System.out.println(Reset_M_HP + "원 획득!!");
           System.out.println("보유금액 : "+User.Money+"원");
           Thread.sleep(1000);
           
           System.out.println("-------------물약획득-------------");
           Per_Potion = random.nextInt(10);	//포션획득 확률
           
           if(Per_Potion == 9) 	//10% 대형물약
           {
        	   User.Potion_big +=1;
        	   System.out.println("대형물약 획득!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% 중형물약
           {
        	   User.Potion_medium += 1;
        	   System.out.println("중형물약 획득!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% 소형물약
           {
        	   User.Potion_small += 1;
        	   System.out.println("소형물약 획득!!");
           }
           else 		//40% 미획득
           {
        	   System.out.println("획득한 물약이 없습니다.");
           }
           
    	   System.out.println("-----------물약 보유현황----------");
   		   System.out.println("소형 물약 : "+User.Potion_small);
   		   System.out.println("중형 물약 : "+User.Potion_medium);
   		   System.out.println("대형 물약 : "+User.Potion_big);
           
           
           System.out.println();
           Clear clear = new Clear();
           
           clear.start();
           try {
   			clear.join();
   			}
   		catch(InterruptedException e)
   		{}
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------모험결과-------------");
           System.out.println(User.name + " Loseㅠ.ㅠ");
        }
		
		 Map.Boss.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

		
}











