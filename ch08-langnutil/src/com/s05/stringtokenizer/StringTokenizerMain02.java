package com.s05.stringtokenizer;

import java.util.StringTokenizer;

public class StringTokenizerMain02 {
	public static void main(String[] args) {
		String source = "2021-01-12 15:47:30";
		StringTokenizer st = new StringTokenizer(source, "-: ");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
