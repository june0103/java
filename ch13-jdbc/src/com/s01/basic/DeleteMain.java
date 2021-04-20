package com.s01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMain {
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
			sql = "DELETE FROM test1 WHERE id='sky'";

			// JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();

			// JDBC 수행 4단계 : 테이블에서 행을 삭제한 후 삭제한 행의 갯수를 반환
			int count = stmt.executeUpdate(sql);
			System.out.println(count + "개 행을 삭제했니다.");

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
