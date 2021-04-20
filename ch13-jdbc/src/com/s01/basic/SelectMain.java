package com.s01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "scott";
		String db_password = "tiger";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			// JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);

			// JDBC 수행 2단계 : connection 객체 생성(ID, 비밀번호 인증)
			conn = DriverManager.getConnection(db_url, db_id, db_password);

			// SQL문 작성
			sql = "SELECT * FROM test1";

			// JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();

			// JDBC 수행 4단계 : SQL문 실행해서 결과집합을 만들고 ResultSet 객체 담아서 반환
			// executeQuery는 SELECT할대 사용하는 함수다
			rs = stmt.executeQuery(sql);

			System.out.println("ID\t나이");
			// ResultSet 객체에 보관된 결과집합에 접근해서 행단위로 데이터를 추출
			while (rs.next()) {
				// 컬럼명을 통해서 데이터를 반환
//				System.out.print(rs.getString("id"));
//				System.out.print("\t");
//				System.out.println(rs.getInt("age"));

				// 컬럼 인덱스를 통해서 데이터를 반환
				System.out.print(rs.getString(1));// id
				System.out.print("\t");
				System.out.println(rs.getInt(2));// age
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
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
