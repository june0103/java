package DungeonStriker;

import java.util.Scanner;
import java.util.Random;

public class Shop {
	
	String Unit_Name;
	
	boolean Shop_flag = true;	//상점 반복동작 플래그
	boolean Buy_Potion_flag = true;	//포션구매 반복동작 플래그
	boolean Sell_Potion_flag = true;	//포션판매 반복동작 플래그
	boolean Buy_Unit_flag = true;	//유닛뽑기 반복동작 플래그
	boolean first_Buyunit_flag = true;	//처음시작시 무료뽑기 플래그

	int Buy_No;	//물약구매 갯수
	int Sell_No;	//물약판매 갯수
	int Unit_per;	//유닛뽑기시 공격형, 방어형 랜덤변수
	int Unit_Att_per;	//유닛뽑기시 속성 랜덤변수
	static int Unit_No=0;	//유닛을뽑고 리스트에 저장하기위한 용도로사용
	int first_Buyunit=0;	//유닛 무료뽑기 3번 카운트사용 
	
	
	int Shop_Select;	//상점기능 선택
	int Potion_Select;	//포션종류선택
	int BuyUnit_Select;	//유닛뽑기 선택
	
	
	
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	
	
	
	public void Shop_List()
	{
		System.out.println("--------------메뉴를 선택해 주세요--------------");
		System.out.println("1.물약구매    2.물약판매   3.유닛뽑기    4.나가기");
		System.out.println("----------------------------------------------");
	}
	
	public void Buy_Potion_List()
	{
		System.out.println("---------------물약 보유현황-------------");
		System.out.println("소형 물약 : "+User.Potion_small);
		System.out.println("중형 물약 : "+User.Potion_medium);
		System.out.println("대형 물약 : "+User.Potion_big);
		System.out.println("---------구매하실 물약을 선택해 주세요----------");
		System.out.println("1.소형물약  2.중형물약 3.대형물약  4.나가기");
		System.out.println("----------------------------------------");
	}
	
	public void Sell_Potion_List()
	{
		System.out.println("---------------------물약 보유현황-------------------");
		System.out.println("소형 물약 : "+User.Potion_small);
		System.out.println("중형 물약 : "+User.Potion_medium);
		System.out.println("대형 물약 : "+User.Potion_big);
		System.out.println("-------------판매하실 물약을 선택해 주세요-------------");
		System.out.println("1.소형물약       2.중형물약       3.대형물약       4.나가기");
		System.out.println("----------------------------------------------------");
	}
	
	public void Buy_Unit_List()
	{
		System.out.println("-----메뉴를 선택해 주세요-----");
		System.out.println("1.뽑기(1회 300원)   2.나가기");
		System.out.println("------------------------");
	}
	
	public void Buy_Potion_s()
	{
		System.out.println("----소형물약(10원)----");
		System.out.println("소지금 : "+User.Money);
		System.out.println("구매 수량을 입력하세요.");
		Buy_No = sc.nextInt();
		
		if(User.Money > Buy_No*10) //보유금액이 많을경우 구매진행
		{
			User.Potion_small += Buy_No;	//입력한 수량만큼 포션증가
			User.Money -= Buy_No*10;	//입력한 수량만큼의 금액 지불
			
			
			System.out.println("-------구매 완료------");
			System.out.println("구매 금액 : "+Buy_No*10);	//구매금액 보여주기
			System.out.println("-----물약 보유현황-----");	//물약구매완료 후 보유갯수 보여주기
			System.out.println("소형 물약 : "+User.Potion_small);	
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
		}
		else	//보유금액이 적을경우 구매실패
			System.out.println("금액이 부족합니다.");
			System.out.println("소지금 : "+User.Money);
	}
	
	public void Buy_Potion_m()
	{
		System.out.println("----중형물약(50원)----");
		System.out.println("소지금 : "+User.Money);
		System.out.println("구매 수량을 입력하세요.");
		Buy_No = sc.nextInt();
		
		if(User.Money > Buy_No*50)
		{
			User.Potion_medium += Buy_No;
			User.Money -= Buy_No*50;
			System.out.println("-------구매 완료------");
			System.out.println("구매 금액 : "+Buy_No*50);
			System.out.println("-----물약 보유현황-----");
			System.out.println("소형 물약 : "+User.Potion_small);
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
		}
		else
			System.out.println("금액이 부족합니다.");
			System.out.println("소지금 : "+User.Money);
	}
	
	public void Buy_Potion_b()
	{
		System.out.println("----대형물약(100원)----");
		System.out.println("소지금 : "+User.Money);
		System.out.println("구매 수량을 입력하세요.");
		Buy_No = sc.nextInt();
		
		if(User.Money > Buy_No*100)
		{
			User.Potion_big += Buy_No;
			User.Money -= Buy_No*100;

			System.out.println("-------구매 완료------");
			System.out.println("구매 금액 : "+Buy_No*100);
			System.out.println("-----물약 보유현황-----");
			System.out.println("소형 물약 : "+User.Potion_small);
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
		}
		else
			System.out.println("금액이 부족합니다.");
			System.out.println("소지금 : "+User.Money);
	}
	
	public void Sell_Potion_s()
	{
		System.out.println("----소형물약(8원)----");	
		System.out.println("소형 물약 : "+User.Potion_small);	//소형물약 소유개수 보여주기
		System.out.println("판매 수량을 입력하세요.");
		Sell_No = sc.nextInt();	//판매개수입력
		
		if(User.Potion_small >= Sell_No)	//판매개수가 보유개수보다 적을시 판매진행
		{
			User.Potion_small -= Sell_No;	//판매수량만큼 물약개수 차감
			User.Money += Sell_No*8;		//입력한수량만큼 판매된 금액 합산
			
			System.out.println("-------판매 완료------");
			System.out.println("판매 금액 : "+Sell_No*8);
			System.out.println("-----물약 보유현황-----");	//판매하고 남은 물약개수 보여주기
			System.out.println("소형 물약 : "+User.Potion_small);
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
			System.out.println("소지금 : "+User.Money);
		}
		else
			System.out.println("보유하고 있는 수량이 적습니다.");
			
	}
	
	public void Sell_Potion_m()
	{
		System.out.println("----중형물약(40원)----");
		System.out.println("중형 물약 : "+User.Potion_medium);
		System.out.println("판매 수량을 입력하세요.");
		Sell_No = sc.nextInt();
		
		if(User.Potion_medium >= Sell_No)
		{
			User.Potion_medium -= Sell_No;
			User.Money += Sell_No*40;
		
			System.out.println("--------판매 완료--------");
			System.out.println("판매 금액 : "+Sell_No*40);
			System.out.println("-------물약 보유현황------");
			System.out.println("소형 물약 : "+User.Potion_small);
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
			System.out.println("소지금 : "+User.Money);
		}
		else
			System.out.println("보유하고 있는 수량이 적습니다.");
	}
	
	public void Sell_Potion_b()
	{
		System.out.println("----대형물약(80원)----");
		System.out.println("대형 물약 : "+User.Potion_big);
		System.out.println("판매 수량을 입력하세요.");
		Sell_No = sc.nextInt();
		
		if(User.Potion_big >= Sell_No)
		{
			User.Potion_big -= Sell_No;
			User.Money += Sell_No*80;
		
			System.out.println("-------판매 완료------");
			System.out.println("판매 금액 : "+Sell_No*80);
			System.out.println("-----물약 보유현황-----");
			System.out.println("소형 물약 : "+User.Potion_small);
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
			System.out.println("소지금 : "+User.Money);
		}
		else
			System.out.println("보유하고 있는 수량이 적습니다.");
	}
	
	public void Buy_Unit1()
	{
		
		System.out.println("------뽑는 중------");	//유닛뽑기 시작
		//0 1 2 3 4 5 6 7 8 9
		
		Unit_per = rd.nextInt(2);	//유닛의 타입 공격형 방어형을 선택하기위한 0,1 랜덤 0은 공격형 1은 방어형
		Unit_Att_per = rd.nextInt(4);	//유닛의 속성을 선택하기위한 랜덤  0풀 1땅 2물 3불
		
		if(Unit_per == 0 && Unit_Att_per == 0)	//공격형과 풀속성 랜덤숫자가 나올시
		{
			System.out.println("풀속성 공격형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);	//유닛리스트에 워리어객체를 입력
			
			System.out.println("----------보유 유닛 목록----------");
		
			for(int a=0; a<Unit_No+1; a++)	//유닛을 뽑고 보유유닛목록을 출력
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
		
			
			Unit_No += 1;	//유닛을 뽑고 1씩증가하게하여 유닛리스트 배열의 자리를 지정
		}
		
		else if(Unit_per == 0 && Unit_Att_per == 1) //공격형 땅
		{
			System.out.println("땅속성 공격형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);
			
			System.out.println("----------보유 유닛 목록----------");
			
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
		
			
			Unit_No += 1;
		}
		
		else if(Unit_per == 0 && Unit_Att_per == 2) //공격형 물
		{
			System.out.println("물속성 공격형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);
			
			System.out.println("----------보유 유닛 목록----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
		
			
			Unit_No += 1;
		}

		else if(Unit_per == 0 && Unit_Att_per == 3) //공격형 불
		{
			System.out.println("불속성 공격형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);
			
			System.out.println("----------보유 유닛 목록----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
			
			
			Unit_No += 1;
		}
		
		else if(Unit_per == 1 && Unit_Att_per ==0) //방어형 풀
		{
			System.out.println("풀속성 방어형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------보유 유닛 목록----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
			
			
			Unit_No += 1;
		}

		else if(Unit_per == 1 && Unit_Att_per ==1) //방어형 땅
		{
			System.out.println("땅속성 방어형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------보유 유닛 목록----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
			
			
			Unit_No += 1;
		}
		
		else if(Unit_per == 1 && Unit_Att_per ==2)	//방어형 물
		{
			System.out.println("물속성 방어형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------보유 유닛 목록----------");
	
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
			
			
			
			Unit_No += 1;
		}

		else if(Unit_per == 1 && Unit_Att_per ==3)	//방어형 불
		{
			System.out.println("불속성 방어형 유닛!!");
			System.out.println("유닛의 이름을 입력해주세요.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------보유 유닛 목록----------");
	
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
			}
			
			
			
			Unit_No += 1;
		}
	}
	
	
	
	
	
	public void Shopping()
	{
		Shop_flag = true;
		while(Shop_flag)
		{
			Shop_List();				
			Shop_Select = sc.nextInt();
	
			switch(Shop_Select)
			{
			case 1:
				Buy_Potion_flag = true;
				while(Buy_Potion_flag)
				{
					Buy_Potion_List();
					Potion_Select = sc.nextInt();
		
		
					switch(Potion_Select)
					{
					case 1:
						Buy_Potion_s();
						break;
			
					case 2:
						Buy_Potion_m();
						break;
			
					case 3:
						Buy_Potion_b();
						break;
				
					case 4:
						Buy_Potion_flag = false;
					}
				}
				break;
		
			case 2:
				Sell_Potion_flag = true;
				while(Sell_Potion_flag)
				{
					Sell_Potion_List();
					Potion_Select = sc.nextInt();
		
		
					switch(Potion_Select)
					{
					case 1:
						Sell_Potion_s();
						break;
			
					case 2:
						Sell_Potion_m();
						break;
			
					case 3:
						Sell_Potion_b();
						break;
				
					case 4:
						Sell_Potion_flag = false;
					}
				}
				break;
				
			
			case 3:
				Buy_Unit_flag = true;
				while(Buy_Unit_flag)
				{
					Buy_Unit_List();
					BuyUnit_Select = sc.nextInt();
		
					switch(BuyUnit_Select)
					{
					case 1:
						
						if(first_Buyunit_flag)
						{
							Buy_Unit1();
							first_Buyunit+=1;
							System.out.println("무료뽑기 : " + first_Buyunit +"/3");
							if(first_Buyunit==3)
							{
								System.out.println("무료뽑기 종료. 메인으로 이동하여 주세요.");
								first_Buyunit_flag = false;
							}
						}
						else if(User.Money>=300)
						{
							Buy_Unit1();
							User.Money -= 300;
							System.out.println("소지금 : "+User.Money);
						}
						else
						{
							System.out.println("금액이 부족합니다.");
						}
						break;
			
				
					case 2:
						Buy_Unit_flag = false;
						
					}
				}
				break;
		
			
			case 4:
				Shop_flag = false;
				break;
				
			default : 
				System.out.println("잘못된 입력입니다.");
		
				

	}
	
	}
	
	}
	
	
}
