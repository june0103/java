package DungeonStriker;

public class Menu  {

	public void Menu_Select() 	//메인메뉴
	{
		//for(int i=0; i<120; i++)
		//{
		//	System.out.println("");
		//}
		System.out.println("--------------------메인 메뉴--------------------");
		System.out.println("1.던전   2.상점   3.인벤토리    4.등급강화    5.종료");
		System.out.println("------------------------------------------------");
	}
	
	public void Battle_Select()	//전투시 행동선택
	{
		System.out.println("-------------------------------------");
		System.out.println("1.공격          2.물약사용          3.나가기");
		System.out.println("-------------------------------------");
		
	}
		
	public static void Bag()	//가방선택시 선택
	{
		System.out.println("-------------인벤토리-------------");
		System.out.println("1.유저정보   2.보유 유닛   3.나가기");
		System.out.println("---------------------------------");
		
	}
	
}
