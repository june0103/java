public class ArrayMain07 {

	public static void main(String[] args) {
		
		if(args.length>0) {
			//ArrayMain07�� �����ϸ鼭 �ܺο��� ������ �����Ͱ� ������� args�� ����Ű�� �迭�� ����ִ�.
			
			for(int i = 0; i<args.length; i++) {
				System.out.println(args[i]);
			}
			
		}
		else {
			//ArrayMain07�� �����ϸ鼭 �ܺο��� ������ �����Ͱ� ������
			
			System.out.println("�Է��� ������ �����ϴ�.");
			
		}
		
	}
}

//Run As > Run configurations > Arguments > Program Arguments�� �ؽ�Ʈ �Է�. ����� �����ν�.������
