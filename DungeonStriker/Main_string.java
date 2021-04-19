package DungeonStriker;

public class Main_string extends Thread {

	public void run()		//게임시작시 게임명과 게임설명을 슬립기능을 사용하여 에니메이션 효과
	{
		try		
		{	
			String s  ="============================================================================================================";
			String s00="";
			String s0 ="              .S_sSSs     .S       S.    .S_sSSs      sSSSSs    sSSs    sSSs_sSSs     .S_sSSs";
			String s1 ="             .SS~YS%%b   .SS       SS.  .SS~YS%%b    d%%%%SP   d%%SP   d%%SP~YS%%b   .SS~YS%%b";
			String s2 ="             S%S   `S%b  S%S       S%S  S%S   `S%b  d%S'      d%S'    d%S'     `S%b  S%S   `S%b";
			String s3 ="             S%S    S%S  S%S       S%S  S%S    S%S  S%S       S%S     S%S       S%S  S%S    S%S"; 
			String s4 ="             S%S    S&S  S&S       S&S  S%S    S&S  S&S       S&S     S&S       S&S  S%S    S&S"; 
			String s5 ="             S&S    S&S  S&S       S&S  S&S    S&S  S&S       S&S_Ss  S&S       S&S  S&S    S&S"; 
			String s6 ="             S&S    S&S  S&S       S&S  S&S    S&S  S&S       S&S~SP  S&S       S&S  S&S    S&S"; 
			String s7 ="             S&S    S&S  S&S       S&S  S&S    S&S  S&S sSSs  S&S     S&S       S&S  S&S    S&S"; 
			String s8 ="             S*S    d*S  S*b       d*S  S*S    S*S  S*b `S%%  S*b     S*b       d*S  S*S    S*S"; 
			String s9 ="             S*S   .S*S  S*S.     .S*S  S*S    S*S  S*S   S%  S*S.    S*S.     .S*S  S*S    S*S"; 
			String s10="             S*S_sdSSS    SSSbs_sdSSS   S*S    S*S   SS_sSSS   SSSbs   SSSbs_sdSSS   S*S    S*S"; 
			String s11="             SSS~YSSY      YSSP~YSSY    S*S    SSS    Y~YSSY    YSSP    YSSP~YSSY    S*S    SSS";  
			String s12="                                         sp                                           sp       ";
			String s13="                                          Y                                            Y       ";
			String s14="";
			String s15="";
			String s16="                     sSSs  sdSS_SSSSSSbs   .S_sSSs     .S   .S    S.     sSSs   .S_sSSs";
			String s17="                    d%%SP  YSSS~S%SSSSSP  .SS~YS%%b   .SS  .SS    SS.   d%%SP  .SS~YS%%b";
			String s18="                   d%S'         S%S       S%S   `S%b  S%S  S%S    S&S  d%S'    S%S   `S%b";
			String s19="                   S%|          S%S       S%S    S%S  S%S  S%S    d*S  S%S     S%S    S%S";
			String s20="                   S&S          S&S       S%S    d*S  S&S  S&S   .S*S  S&S     S%S    d*S";
			String s21="                   Y&Ss         S&S       S&S   .S*S  S&S  S&S_sdSSS   S&S_Ss  S&S   .S*S";
			String s22="                   `S&&S        S&S       S&S_sdSSS   S&S  S&S~YSSY%b  S&S~SP  S&S_sdSSS";
			String s23="                     `S*S       S&S       S&S~YSY%b   S&S  S&S    `S%  S&S     S&S~YSY%b";
			String s24="                      l*S       S*S       S*S   `S%b  S*S  S*S     S%  S*b     S*S   `S%b";
			String s25="                     .S*P       S*S       S*S    S%S  S*S  S*S     S&  S*S.    S*S    S%S";
			String s26="                    SS*S        S*S       S*S    S&S  S*S  S*S     S&   SSSbs  S*S    S&S";
			String s27="                   YSS'         S*S       S*S    SSS  S*S  S*S     SS    YSSP  S*S    SSS";
			String s28="                                sp        sp          sp   sp                  sp        ";
			String s29="                                Y         Y           Y    Y                   Y";
			String s30="";
			String s31="==============================================Made_by. June.k===============================================";
			
			String s_[][]  ={{s},		//문자를 위에서부터 아래로 표현하기위하여 2차원배열 생성
							{s00},
							{s0},
							{s1},
							{s2},
							{s3},
							{s4},
							{s5},
							{s6},
							{s7},
							{s8},
							{s9},
							{s10},
							{s11},
							{s12},
							{s13},
							{s14},
							{s15},
							{s16},
							{s17},
							{s18},
							{s19},
							{s20},
							{s21},
							{s22},
							{s23},
							{s24},
							{s25},
							{s26},
							{s27},
							{s28},
							{s29},
							{s30}};
			
			for(int i=0; i<33; i++) 	//DungeonStriker 위에서 아래로 출력 반복문
		    {
		    	System.out.println(s_[i][0]);
		    	Thread.sleep(300);
		    }
			for(int i=0; i<s31.length(); i++)
			{
				System.out.print(s31.charAt(i));
				Thread.sleep(10);
			}
			
			Thread.sleep(500);
		    System.out.println();
		    
		   
		    
		    System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			//System.out.println("던전스트라이커는 턴제 방식의 RPG게임입니다.");
			 String s32 = "던전스트라이커는 턴제 방식의 RPG게임입니다.\n";
			for(int i=0; i<s32.length(); i++)
			{
				System.out.print(s32.charAt(i));
				Thread.sleep(50);
			}
			System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			//System.out.println("유닛의 종류는 워리어와 디펜더로 나눠집니다.\n");
			//System.out.println("워리어 : 공격시 추가데미지 10%    디펜더 : 피격시 데미지 10% 감소");
			 String s33 = "유닛의 종류는 워리어와 디펜더로 분류됩니다.\n\n";
			 String s34 = "워리어 : 공격시 추가데미지 10%    디펜더 : 피격시 데미지 10% 감소\n";
			 for(int i=0; i<s33.length(); i++)
				{
					System.out.print(s33.charAt(i));
					Thread.sleep(50);
				}
			 for(int i=0; i<s34.length(); i++)
				{
					System.out.print(s34.charAt(i));
					Thread.sleep(50);
				}
			System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			//System.out.println("모든 유닛과 몬스터에는 풀, 땅, 물, 불의 속성을 가지고있습니다.\n");
			//System.out.println("풀 - 땅, 땅 - 물, 물 - 불, 불 - 풀 상성 효과를 가지고있습니다.");
			 String s35 = "모든 유닛과 몬스터에는 풀, 땅, 물, 불의 속성을 가지고있습니다.\n\n";
			 String s36 = "풀 - 땅, 땅 - 물, 물 - 불, 불 - 풀 상성 효과를 가지고있습니다.\n";
			 for(int i=0; i<s35.length(); i++)
				{
					System.out.print(s35.charAt(i));
					Thread.sleep(50);
				}
			 for(int i=0; i<s36.length(); i++)
				{
					System.out.print(s36.charAt(i));
					Thread.sleep(50);
				}
			System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			String s37 = "상점에서는 물약을 구매하거나 판매 할 수있고, 유닛을 뽑을 수 있습니다.\n\n";
			String s38 = "유닛뽑기는 50%확률로 워리어와 디펜더, 25%확률로 속성이 정해집니다.\n";
			 for(int i=0; i<s37.length(); i++)
				{
					System.out.print(s37.charAt(i));
					Thread.sleep(50);
				}
			 for(int i=0; i<s38.length(); i++)
				{
					System.out.print(s38.charAt(i));
					Thread.sleep(50);
				}
			System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			String s39 = "등급강화로 일정 골드를 지불하여 유닛의 능력치를 강화 할 수 있습니다.\n\n";
			String s40 = "체력은 0 ~ 100, 공격력은 0 ~ 10 중 랜덤하게 증가합니다.\n";
			 for(int i=0; i<s39.length(); i++)
				{
					System.out.print(s39.charAt(i));
					Thread.sleep(50);
				}
			 for(int i=0; i<s40.length(); i++)
				{
					System.out.print(s40.charAt(i));
					Thread.sleep(50);
				}
			System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			String s41 = "플레이어만의 유닛조합으로 던전을 깨며 유닛을 육성시켜보세요.\n\n";
			String s42 = "던전스트라이커의 마지막 목표! 던전보스를 클리어하여 주세요!!\n";
			 for(int i=0; i<s41.length(); i++)
				{
					System.out.print(s41.charAt(i));
					Thread.sleep(50);
				}
			 for(int i=0; i<s42.length(); i++)
				{
					System.out.print(s42.charAt(i));
					Thread.sleep(50);
				}
			System.out.println("--------------------------------------------------------------");
			
			
		
		}
		catch(Exception e)
		{
			
		}
		
		
	}
}
