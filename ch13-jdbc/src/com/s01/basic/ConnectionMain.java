package com.s01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMain {
	public static void main(String[] args) {
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "scott";
		String db_password = "tiger";
		try {
			// JDBC 수행 1단계 : 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");

			// JDBC 수행 2단계 : Connection객체 생성(ID,비밀번호 인증)
			Connection conn = DriverManager.getConnection(db_url, db_id, db_password);
			System.out.println("Connection 객체가 생생되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
