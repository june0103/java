package DungeonStriker;

import java.util.Scanner;

public class User {
	
	boolean Bag_flag = true;
	
	static String name;
	
	static int Money;
	static int Potion_small = 0 ;
	static int Potion_medium = 0 ;
	static int Potion_big = 0;
	int Bag_Select;
	
	Scanner sc = new Scanner(System.in);
	
	
	
		public User(String User_name, int User_money) //이름과 돈 파라미터를 갖는 유저 생성자
		{
			this.name = User_name;
			User.Money = User_money;
		}

		
		void getUserInfo()	//유저정보보여주기
		{
			System.out.println("------------유저 정보-------------");
			System.out.println("닉네임 : " +User.name);
			System.out.println("보유유닛 : " +Shop.Unit_No);
			System.out.println("보유골드 : " +User.Money);
			System.out.println("소형 물약 : "+User.Potion_small);
			System.out.println("중형 물약 : "+User.Potion_medium);
			System.out.println("대형 물약 : "+User.Potion_big);
			System.out.println("플레이시간 : "+Timer.time_h +"시간  "+Timer.time_m+"분  "+Timer.time_s+"초");
			System.out.println("---------------------------------");
			System.out.println("나가시려면 아무키나 입력해주세요.");
		}
		
		void bag() throws InterruptedException
		{
			
			Bag_flag = true;
			
			while(Bag_flag)
			{
				Menu.Bag();
			
				Bag_Select = sc.nextInt();
			
			
			
				switch(Bag_Select)
				{
				case 1: //유저정보
					getUserInfo();
					
					 try		//아무키나 입력
				        {
				            System.in.read();
				        }  
				        catch(Exception e)
				        {}  
					break;	//유저정보 종료
			
				case 2: 	//보유유닛 시작
					boolean Unit_info_flag = true;
					
			Loop1 : while(Unit_info_flag) //유닛정보보기 반복문
					{
						if(Unit.UnitList[0]==null) //보유 유닛이 0명일때
						{
							System.out.println("현재 보유하고있는 유닛이 없습니다.");
							break Loop1;
						}
						
						else	//유닛을 보유중일때 유닛정보보기시작
						{
							System.out.println("----------보유 유닛 목록----------");
				
							for(int a=0; a<Shop.Unit_No; a++) //유닛뽑기에서 사용된 배열 인덱스설정한 변수만큼 보유유닛목록 출력
							{
								System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		레벨 : "+ Unit.UnitList[a].Level );
							}
							System.out.println("---------------------------------");
							System.out.println("1. 상세 유닛 정보    2. 나가기");
							int Unitinfo_menu = sc.nextInt();	
						
							if(Unitinfo_menu ==1)	//상세정보 보기
							{
								System.out.println("보유 유닛 목록에서 원하는 유닛의 번호를 입력해주세요.");
					
								int Unitinfo = sc.nextInt();
								Unit.UnitList[Unitinfo-1].getInfo();	//입력된 숫자의 배열인덱스 유닛의 정보를 호출
								System.out.println("나가시려면 아무키나 입력해주세요.");
								try		//아무키나 입력
						        {
						            System.in.read();
						        }  
						        catch(Exception e)
						        {}  
								
							}
							else if(Unitinfo_menu == 2)	//나가기
							{
								Unit_info_flag = false;	//유닛정보보기 반복문 플래그 반전
							}
						
							else //그외값 입력시
								System.out.println("잘못된 입력입니다.");
						}
					}
						break; //보유유닛 종료
					
				case 3:
					Bag_flag = false;
					break;
				default :  //그외값 입력시
				System.out.println("잘못된 입력입니다.");
				break;
			}
			
		
			
			
			}
		}
		
		
	
		

}
