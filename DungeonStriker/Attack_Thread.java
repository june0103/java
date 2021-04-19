package DungeonStriker;

import java.io.FileInputStream;

import javazoom.jl.player.*;

class Attack_Thread implements Runnable {	//Thread를 상속받아 사용하면 재사용이 불가능하지만 Runnable는 재사용이 가능하다
	
	public void run() //쓰레드를 통해서 수행할 내용들
	{
		
		try
		{
			FileInputStream file = new FileInputStream("C:\\Users\\June\\Music\\attack_bgm.mp3");
			Player music = new Player(file);
			System.out.println("===================================================================================="); 
			System.out.println("                                                    w5"); 
			System.out.println("                                                       zy ,  Wj9");  
			System.out.println("                                              DZ  ,w   D   y Z j5"); 
			System.out.println("                                                 ,,  ,D B  w  ,   Z,"); 
			System.out.println("                                                        w  ,");  
			System.out.println("                                                              Z ,Z");   
			System.out.println("                                                                    D5j5 wZZZZZZ");         
			System.out.println("                                                   ,                  y wZZZZZZZZ");        
			System.out.println("                                                 Wk               ZZZZE DZZZZZZZZ");        
			System.out.println("                                              WBw                Z8yyDZZ ZZZZZZZZ");        
			System.out.println("                   5ZZZZZ          W       5Zzy           ,BZZZZZZZZZ ZZ  ZZZZZD");         
			System.out.println("                  ZZZZZZZZ        Z5   ,ZZZ       jZZZZZZZZZZZZjzzzzzZE");
			System.out.println("                  ZZZZZZZZ        wZ Dy8           ZZZZ        BZwWZZ");  
			System.out.println("                  ZZZZZZZZ    8ZZZZD                         ZZZZ8ZZ");                     
			System.out.println("                  ,ZZZZZZ jZZZZy    WZ5                      ZZZZZ");                      
			System.out.println("             ZZZZZZWWy5WzZyy           W                   ZZZZZZ");          
			System.out.println("           ZZZ yWWjy5yyy                                 ZZZZZZ");  
			System.out.println("        jZZZZZ ZZZZZ                                   ZZZZZZZ");
			System.out.println("     ,BZZZZZZZZ59j                                    ZZZZZZZ");  
			System.out.println("===================================================================================="); 
			music.play();
		}
		catch(Exception e)
		{
			
		}
		
	}


}



                         



//String s1 ="                                                    w5"; 
//String s1 ="                                                       zy ,  Wj9";  
//String s1 ="                                              DZ  ,w   D   y Z j5"; 
//String s1 ="                                                 ,,  ,D B  w  ,   Z,"; 
//String s1 ="                                                        w  ,";  
//String s1 ="                                                              Z ,Z";   
//String s1 ="                                                                    D5j5 wZZZZZZ";         
//String s1 ="                                                   ,                  y wZZZZZZZZ";        
//String s1 ="                                                 Wk               ZZZZE DZZZZZZZZ";        
//String s1 ="                                              WBw                Z8yyDZZ ZZZZZZZZ";        
//String s1 ="                   5ZZZZZ          W       5Zzy           ,BZZZZZZZZZ ZZ  ZZZZZD";         
//String s1 ="                  ZZZZZZZZ        Z5   ,ZZZ       jZZZZZZZZZZZZjzzzzzZE";
//String s1 ="                  ZZZZZZZZ        wZ Dy8           ZZZZ        BZwWZZ";  
//String s1 ="                  ZZZZZZZZ    8ZZZZD                         ZZZZ8ZZ";                     
//String s1 ="                  ,ZZZZZZ jZZZZy    WZ5                      ZZZZZ";                      
//String s1 ="             ZZZZZZWWy5WzZyy           W                   ZZZZZZ";          
//String s1 ="           ZZZ yWWjy5yyy                                 ZZZZZZ";  
//String s1 ="        jZZZZZ ZZZZZ                                   ZZZZZZZ";
//String s1 ="     ,BZZZZZZZZ59j                                    ZZZZZZZ";                           
