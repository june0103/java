package DungeonStriker;

import java.util.Scanner;
import java.util.Random;
																//Attacker(String Unit_name, int Unit_HP, int Unit_Atk, int Unit_Grad, int Unit_Level, int Unit_Att)
public class play {												//Monster(String name, int HP, int Atk, int Att)

	public static void Unit_Select_Battle()	//던전파티 구성메소드
	{
		Scanner sc = new Scanner(System.in);
	
		 
		System.out.println("현재 보유하고있는 유닛입니다.");
		
		System.out.println("----------보유 유닛 목록----------");
	
		for(int a=0; a<Shop.Unit_No; a++)	//보유유닛 반복문을사용하여 출력
		{
			System.out.println(a+1 + "." +Unit.UnitList[a].name +" 레벨 : "+ Unit.UnitList[a].Level );
		}
		System.out.println("---------------------------------");
		
		System.out.println("던전에 도전 할 유닛을 선택해주세요!! (3유닛)");
		System.out.println("---------------------------------");
		System.out.println("첫번째 유닛을 선택해주세요!!");
		int choice1 = sc.nextInt();

		Unit.UnitList_battle[0] = Unit.UnitList[choice1-1];	//보유유닛목록에서 선택한 유닛을 배틀유닛리스트에 입력
		for(int a=0; a<1; a++)
		{
			System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level );
		}
		System.out.println("---------------------------------");
		System.out.println("두번째 유닛을 선택해주세요!!");
		int choice2 = sc.nextInt();

		Unit.UnitList_battle[1] = Unit.UnitList[choice2-1];	//보유유닛목록에서 선택한 유닛을 배틀유닛리스트에 입력
		for(int a=0; a<2; a++)
		{
			System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level );
		}
		System.out.println("---------------------------------");
		System.out.println("세번째 유닛을 선택해주세요!!");
		int choice3 = sc.nextInt();

		Unit.UnitList_battle[2] = Unit.UnitList[choice3-1];
		for(int a=0; a<3; a++)
		{
			System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" 레벨 : "+ Unit.UnitList_battle[a].Level );
		}
		System.out.println("---------------------------------");
	}


	public static void main(String []args) throws InterruptedException //인터럽트 지금 진행하고있는 쓰레드가 하고있는 일을 멈추고 다른일을 하라고 알려줄때 사용
	 {																	//sleep, join을 사용할 때 Thread가 대기 상태로 들어갔다가 깨어나지 못 할때 발생하는 예외
		 Scanner sc = new Scanner(System.in);
		 Random random = new Random();
		 
		 char Play;		//종료하기 

		 int Menu_Select;	//메인메뉴 선택
		 int Map_Select;	//맵 선택
		 int Map_G_Select;	//숲 스테이지선택
		 int Map_L_Select;	//땅 스테이지선택
		 int Map_W_Select;	//물 스테이지선택
		 int Map_F_Select;	//불 스테이지서낵
		 
		 String Player_name;	//스캐너로 유저이름 입력
		 
		 Menu menu = new Menu();	//메뉴클래스 객체화
		 Map map = new Map();		//맵클래스 객체화
		 Shop shop = new Shop();	//상점클래스 객체화
		 BGM bgm = new BGM();		//게임 BGM쓰레드 객체화
		 Timer timer = new Timer();	//타이머쓰레드 객체화
		 Main_string ms = new Main_string();//게임설명 쓰레드 객체화
		 
		 //timer.start();	//타이머쓰레드 시작
		// bgm.start();	//게임 BGM쓰레드 시작
		 //Thread.sleep(1000);
		// ms.start();	//게임설명 쓰레드 시작

//------------------------------------------------------------------------------------------------------------------------	
		try {
			ms.join();	//게임설명쓰레드 종료될때까지 대기
			}
		catch(InterruptedException e)
		{}
		
		Runnable Loading = new Loading();	//로딩 Runnable
		Thread Loading_s = new Thread(Loading);		 
		Loading_s.start();							//로딩시작
		
		try
		{
			Loading_s.join();
		}
		catch(InterruptedException e)
		{
			
		}
		Thread.sleep(500);
		System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {} 
	        
	    Thread.sleep(1000);
	    System.out.println("--------------------------------------------------------------");
	    
	    String s1 = "닉네임을 설정해주세요.\n";
	    for(int i=0; i<s1.length(); i++)
	    {
	    	System.out.print(s1.charAt(i));
	    	Thread.sleep(100);
	    }
		
		Player_name = sc.next();
		
		User User = new User(Player_name, 0);	//유저클래스의 생성자를 통하여 이름을 입력받고 생성, 초기골드 0원
		Thread.sleep(1000);
		
		
		String s2 = DungeonStriker.User.name + "님 환영합니다.\n";
	    for(int i=0; i<s2.length(); i++)
	    {
	    	System.out.print(s2.charAt(i));
	    	Thread.sleep(100);
	    }
	    
		Thread.sleep(1000);
		System.out.println("--------------------------------------------------------------");
		String s3 = "던전을 도전하시려면 유닛이 필요합니다!!\n\n";
		String s4 = "유닛뽑기는 1회에 300원입니다.\n\n";
		String s5 = "하지만!! 처음이시니 3번의 무료기회를 드릴께요!!\n\n";
		String s6 = "상점 - 유닛뽑기로 이동하여 던전을 함께할 유닛을 뽑아 주세요!!\n";
		for(int i=0; i<s3.length(); i++)	//한글자씩 출력
	    {
	    	System.out.print(s3.charAt(i));
	    	Thread.sleep(100);
	    }
		for(int i=0; i<s4.length(); i++)	//한글자씩 출력
	    {
	    	System.out.print(s4.charAt(i));
	    	Thread.sleep(100);
	    }	
		for(int i=0; i<s5.length(); i++)	//한글자씩 출력
	    {
	    	System.out.print(s5.charAt(i));
	    	Thread.sleep(100);
	    }
		for(int i=0; i<s6.length(); i++)	//한글자씩 출력
	    {
	    	System.out.print(s6.charAt(i));
	    	Thread.sleep(100);
	    }
		
		System.out.println("--------------------------------------------------------------");
		Thread.sleep(2000);
		
		while(true)	//강제종료 이전까지 항상반복 
		{
			menu.Menu_Select();	//메인메뉴선택
			
			Menu_Select = sc.nextInt();
					
			switch(Menu_Select) //메인메뉴 선택 조건문
			{
			case 1:	//던전입장
				
				if(Unit.UnitList[0]==null) //보유 유닛이 0명일때
				{
					System.out.println("현재 보유하고있는 유닛이 없습니다.");
					System.out.println("유닛을 뽑아 던전에 재입장 해주세요.");
				}
				
				else	//유닛을 보유하고있을때
				{
					Unit_Select_Battle();	//던전 파티구성 메소드시작
					
					map.Map_Select();		//맵선택
					Map_Select = sc.nextInt();
				
					switch(Map_Select) //맵선택 조건문 시작
					{
					case 1:	//풀던전입장
						
						System.out.println("풀던전 입장 중.....");
		                  map.Map_G();	//풀던전 스테이지선택
		                  Map_G_Select = sc.nextInt(); 
		               
		                  switch(Map_G_Select) //풀던전 스테이지 조건문 시작
		                  {
		                  case 1:	//풀-01 던전 입장
		                	  bgm.suspend();	//배경음 일시정지 . suspend - 쓰레드는 일시정지 상태가 된다
		                	  Unit.Battle_G1(); //풀 - 01 던전 배틀 메소드 시작
		                	  bgm.resume();		//배경음 다시재생
		                	  break;
						case 2:
							  bgm.suspend();
		                	  Unit.Battle_G2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_G3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_G4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_G5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_G6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_G7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_G8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_G9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_G10();
		                	  bgm.resume();
		                	  break;
		                  default : //그외값이 입력됬을때
							  System.out.println("잘못된 입력입니다.");
							  break;
		                  }
					break; //풀던전 종료
									
					case 2: //땅던전 입장
						
						System.out.println("땅던전 입장 중.....");
		                  map.Map_L();
		                  Map_L_Select = sc.nextInt();
		               
		                  switch(Map_L_Select)
		                  {		
		                  case 1:	//땅-01 던전 입장
		                	  bgm.suspend();
		                	  Unit.Battle_L1();
		                	  bgm.resume();
		                	  break;
		                  case 2:
		                	  bgm.suspend();
		                	  Unit.Battle_L2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_L3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_L4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_L5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_L6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_L7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_L8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_L9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_L10();
		                	  bgm.resume();
		                	  break;
		                  default : 
							  System.out.println("잘못된 입력입니다.");
							  break;
		                  }		
					break;				
									
					case 3: //물던전 입장
						System.out.println("물던전 입장 중.....");
		                  map.Map_W();
		                  Map_W_Select = sc.nextInt();
		               
		                  switch(Map_W_Select)
		                  {		
		                  case 1:	//물-01 던전 입장
		                	  bgm.suspend();
		                	  Unit.Battle_W1();
		                	  bgm.resume();
		                	  break;
		                  case 2:
		                	  bgm.suspend();
		                	  Unit.Battle_W2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_W3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_W4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_W5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_W6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_W7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_W8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_W9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_W10();
		                	  bgm.resume();
		                	  break;
		                  default : 
							  System.out.println("잘못된 입력입니다.");
							  break;
		                  }		
					break;				
					
						
					case 4: //불던전 입장
						System.out.println("불던전 입장 중.....");
		                  map.Map_F();
		                  Map_F_Select = sc.nextInt();
		               
		                  switch(Map_F_Select)
		                  {		
		                  case 1:	//불-01 던전 입장
		                	  bgm.suspend();
		                	  Unit.Battle_F1();
		                	  bgm.resume();
		                	  break;
		                  case 2:
		                	  bgm.suspend();
		                	  Unit.Battle_F2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_F3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_F4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_F5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_F6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_F7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_F8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_F9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_F10();
		                	  bgm.resume();
		                	  break;
		                  default : 
							  System.out.println("잘못된 입력입니다.");
							  break;
		                  }		
					break;		
					
					case 5 : 
						bgm.suspend();
						Unit.Battle_Boss();
						bgm.resume();
						break;
					default : 
						System.out.println("잘못된 입력입니다.");
					break;
										
					}
								
			} 
				break; // 던전입장 종료
			case 2:	//상점시작

				shop.Shopping();	//상점메소드 시작
				
				break; //상점 빠져나오기
				
			case 3: //인벤토리
				
				User.bag();
				
				break; //인벤토리 빠져나오기
			
			case 4: // 유닛 등급강화
				Unit.Grad_up();
				
				break;
			case 5: //게임종료
				System.out.println("게임을 종료하시겠습니까? Y/N");
				Play=sc.next().charAt(0);
				
				if(Play == 'Y')
				{
					System.out.println("종료중....");
					Thread.sleep(1000);

					
					
					Thread.sleep(1000);
					System.out.println("종료");
					System.exit(0); //프로그램종료 _ 현재실행하고있는 프로세스강제종료
				}
				
				else
					break;
				
			case 1004:	//테스트용
				System.out.println("운영자의 총애!! 10000원 획득");
				DungeonStriker.User.Money += 10000;
				System.out.println("보유금액 : "+DungeonStriker.User.Money);
				
				break;
				
			default : 
				System.out.println("잘못된 입력입니다.");
				
			}
		
		}
	 }

}
