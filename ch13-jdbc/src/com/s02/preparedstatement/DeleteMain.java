package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			// JDBC 수행 1단계 : 드라이버 로드
			// JDBC 수행 2단계 : connection 객체 생성()
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "DELETE FROM test2 WHERE num=?";

			// JDBC 수행 3단계 : PreparedStatement 객체 생성
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, 1);

			int count = psmt.executeUpdate();
			System.out.println(count + "개 행을 삭제했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, psmt, conn);
		}
	}

}
