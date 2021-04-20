package com.s01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "scott";
		String db_password = "tiger";

		Connection conn = null;
		Statement stmt = null;
		String sql = null;

		try {
			// JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			// JDBC 수행 2단계 : connection 객체 생성(ID, 비밀번호 인증)
			conn = DriverManager.getConnection(db_url, db_id, db_password);

			// SQL문 작성
			sql = "INSERT INTO test1(id,age) VALUES('blue',20)";

			// JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();

			// JDBC 수행 4단계 : SQL문 실행
			// 테이블에 한 개의 행을 삽입 작업 후 삽입한 행의 갯수를 반환
			int count = stmt.executeUpdate(sql);
			System.out.println(count + "개의 행을 추가했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {

				}

			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

}
