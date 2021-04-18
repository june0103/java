public class BreakMain03 {

	public static void main(String[] args) {
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<5; j++) {
				
				//break가 포함된 반복문만 빠져나감
				if(j==3)
					break;
				
				System.out.println("i값 : " + i + ", j값 : " + j);
				
			}
		}
		
	}
}
