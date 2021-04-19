package DungeonStriker;

public class Map{
	
	
	public void Map_Select() //메인메뉴에서 던전선택시 나오는 선택문구
	{
		System.out.println("각 던전마다 속성몬스터가 출현합니다.");
		
		System.out.println("--------------던전을 선택해 주세요-------------");
		System.out.println("1.풀지역   2.땅지역   3.물지역   4.불지역   5.보스");
		System.out.println("---------------------------------------------");
	}
	
	
	public void Map_G()	//풀던전 선택시 스테이지선택 문구
	{
		System.out.println("몬스터가 뒤로갈수록 강해집니다.");
		System.out.println("-----------------------------스테이지를 선택해 주세요-------------------------------");
		System.out.println("1.풀-1  2.풀-2  3.풀-3  4.풀-4  5.풀-5  6.풀-6  7.풀-7  8.풀-8  9.풀-9  10.풀-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	public void Map_L() //땅던전 스테이지선택문구
	{
		System.out.println("몬스터가 뒤로갈수록 강해집니다.");
		System.out.println("-----------------------------스테이지를 선택해 주세요-------------------------------");
		System.out.println("1.땅-1  2.땅-2  3.땅-3  4.땅-4  5.땅-5  6.땅-6  7.땅-7  8.땅-8  9.땅-9  10.땅-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public void Map_W()	//물던전 스테이지선택문구
	{
		System.out.println("몬스터가 뒤로갈수록 강해집니다.");
		System.out.println("-----------------------------스테이지를 선택해 주세요-------------------------------");
		System.out.println("1.물-1  2.물-2  3.물-3  4.물-4  5.물-5  6.물-6  7.물-7  8.물-8  9.물-9  10.물-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	public void Map_F()	//풀던전 스테이지선택문구
	{
		System.out.println("몬스터가 뒤로갈수록 강해집니다.");
		System.out.println("-----------------------------스테이지를 선택해 주세요-------------------------------");
		System.out.println("1.불-1  2.불-2  3.불-3  4.불-4  5.불-5  6.불-6  7.불-7  8.불-8  9.불-9  10.불-10 ");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	static Monster G1 = new Monster("일반오크", 100, 20, 0, 150);
	static Monster G2 = new Monster("일반고블린", 150, 21, 0, 160);
	static Monster G3 = new Monster("일반트롤", 200, 22, 0, 170);
	static Monster G4 = new Monster("일반언데드", 250, 23, 0, 180);
	static Monster G5 = new Monster("일반오우거", 300, 24, 0, 190);
	static Monster G6 = new Monster("정예오크", 350, 25, 0, 200);
	static Monster G7 = new Monster("정예고블린", 400, 26, 0, 210);
	static Monster G8 = new Monster("정예트롤", 450, 27, 0, 220);
	static Monster G9 = new Monster("정예언데드", 500, 28, 0, 230);
	static Monster G10 = new Monster("정예오우거", 550, 29, 0, 240);
	
	static Monster L1 = new Monster("일반오크", 100, 20, 1, 150);
	static Monster L2 = new Monster("일반고블린", 150, 21, 1, 160);
	static Monster L3 = new Monster("일반트롤", 200, 22, 1, 170);
	static Monster L4 = new Monster("일반언데드", 250, 23, 1, 180);
	static Monster L5 = new Monster("일반오우거", 300, 24, 1, 190);
	static Monster L6 = new Monster("정예오크", 350, 25, 1, 200);
	static Monster L7 = new Monster("정예고블린", 400, 26, 1, 210);
	static Monster L8 = new Monster("정예트롤", 450, 27, 1, 220);
	static Monster L9 = new Monster("정예언데드", 500, 28, 1, 230);
	static Monster L10 = new Monster("정예오우거", 550, 29, 1, 240);
	
	static Monster W1 = new Monster("일반오크", 100, 20, 2, 150);
	static Monster W2 = new Monster("일반고블린", 150, 21, 2, 160);
	static Monster W3 = new Monster("일반트롤", 200, 22, 2, 170);
	static Monster W4 = new Monster("일반언데드", 250, 23, 2, 180);
	static Monster W5 = new Monster("일반오우거", 300, 24, 2, 190);
	static Monster W6 = new Monster("정예오크", 350, 25, 2, 200);
	static Monster W7 = new Monster("정예고블린", 400, 26, 2, 210);
	static Monster W8 = new Monster("정예트롤", 450, 27, 2, 220);
	static Monster W9 = new Monster("정예언데드", 500, 28, 2, 230);
	static Monster W10 = new Monster("정예오우거", 550, 29, 2, 240);
	
	static Monster F1 = new Monster("일반오크", 100, 20, 3, 150);
	static Monster F2 = new Monster("일반고블린", 150, 21, 3, 160);
	static Monster F3 = new Monster("일반트롤", 200, 22, 3, 170);
	static Monster F4 = new Monster("일반언데드", 250, 23, 3, 180);
	static Monster F5 = new Monster("일반오우거", 300, 24, 3, 190);
	static Monster F6 = new Monster("정예오크", 350, 25, 3, 200);
	static Monster F7 = new Monster("정예고블린", 400, 26, 3, 210);
	static Monster F8 = new Monster("정예트롤", 450, 27, 3, 220);
	static Monster F9 = new Monster("정예언데드", 500, 28, 3, 230);
	static Monster F10 = new Monster("정예오우거", 550, 29, 3, 240);
	
	static Monster Boss = new Monster("Boss",10,200, 0, 300);
}
