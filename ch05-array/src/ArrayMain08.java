public class ArrayMain08 {

	public static void main(String[] args) {
		//ArrayMain08�� �����ϸ鼭 �ܺο��� ���� 2���� �����ؼ� �����ϱ�
		
		//���޵� �����ʹ� String���� �ν��ϱ� ������ +������ �� ���� �ƴ϶� ���ڿ� ������ �� [�Ʊ���Ʈ���� 10 20 �Է½� 1020����� �Ǵ°��)
		System.out.println(args[0] + args[1]);
		
		//String -> int ��ȯ
		int num = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
		System.out.println(num+num2);
	}
}
