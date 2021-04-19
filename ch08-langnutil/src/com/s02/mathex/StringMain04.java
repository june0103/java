package com.s02.mathex;

public class StringMain04 {
	public static void main(String[] args) {
		// 문자열에서 소문자는 대문자, 대문자는 소문자로 변경

		String str = "abcMDye-4W?EWzz";
		String reslt = "";

		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {

				reslt += String.valueOf(str.charAt(i)).toLowerCase();

			} else if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
				reslt += String.valueOf(str.charAt(i)).toUpperCase();
			} else {
				reslt += str.charAt(i);
			}
		}

		System.out.println(str);
		System.out.println(reslt);
	}

}
