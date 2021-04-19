package com.s02.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class URLMain02 {
	public static void main(String[] args) {
		URL url = null;
		BufferedReader br = null;
		String address = "https://www.naver.com/index.html";
		String line = null;

		try {
			url = new URL(address);
			br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
		}
	}

}
