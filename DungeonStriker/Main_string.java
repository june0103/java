package DungeonStriker;

public class Main_string extends Thread {

	public void run()		//���ӽ��۽� ���Ӹ�� ���Ӽ����� ��������� ����Ͽ� ���ϸ��̼� ȿ��
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
			
			String s_[][]  ={{s},		//���ڸ� ���������� �Ʒ��� ǥ���ϱ����Ͽ� 2�����迭 ����
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
			
			for(int i=0; i<33; i++) 	//DungeonStriker ������ �Ʒ��� ��� �ݺ���
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
			//System.out.println("������Ʈ����Ŀ�� ���� ����� RPG�����Դϴ�.");
			 String s32 = "������Ʈ����Ŀ�� ���� ����� RPG�����Դϴ�.\n";
			for(int i=0; i<s32.length(); i++)
			{
				System.out.print(s32.charAt(i));
				Thread.sleep(50);
			}
			System.out.println("--------------------------------------------------------------");
			Thread.sleep(1000);
			//System.out.println("������ ������ ������� ������� �������ϴ�.\n");
			//System.out.println("������ : ���ݽ� �߰������� 10%    ����� : �ǰݽ� ������ 10% ����");
			 String s33 = "������ ������ ������� ������� �з��˴ϴ�.\n\n";
			 String s34 = "������ : ���ݽ� �߰������� 10%    ����� : �ǰݽ� ������ 10% ����\n";
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
			//System.out.println("��� ���ְ� ���Ϳ��� Ǯ, ��, ��, ���� �Ӽ��� �������ֽ��ϴ�.\n");
			//System.out.println("Ǯ - ��, �� - ��, �� - ��, �� - Ǯ �� ȿ���� �������ֽ��ϴ�.");
			 String s35 = "��� ���ְ� ���Ϳ��� Ǯ, ��, ��, ���� �Ӽ��� �������ֽ��ϴ�.\n\n";
			 String s36 = "Ǯ - ��, �� - ��, �� - ��, �� - Ǯ �� ȿ���� �������ֽ��ϴ�.\n";
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
			String s37 = "���������� ������ �����ϰų� �Ǹ� �� ���ְ�, ������ ���� �� �ֽ��ϴ�.\n\n";
			String s38 = "���ֻ̱�� 50%Ȯ���� ������� �����, 25%Ȯ���� �Ӽ��� �������ϴ�.\n";
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
			String s39 = "��ް�ȭ�� ���� ��带 �����Ͽ� ������ �ɷ�ġ�� ��ȭ �� �� �ֽ��ϴ�.\n\n";
			String s40 = "ü���� 0 ~ 100, ���ݷ��� 0 ~ 10 �� �����ϰ� �����մϴ�.\n";
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
			String s41 = "�÷��̾�� ������������ ������ ���� ������ �������Ѻ�����.\n\n";
			String s42 = "������Ʈ����Ŀ�� ������ ��ǥ! ���������� Ŭ�����Ͽ� �ּ���!!\n";
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
