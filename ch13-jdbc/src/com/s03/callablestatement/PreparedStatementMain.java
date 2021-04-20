package com.s03.callablestatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.s02.preparedstatement.DBConnection;

public class PreparedStatementMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		String name = "Lee"; // 회원이름
		int pay = 2500; // 인상된 급여

		try {
			// JDBC수행 1,2단계
			conn = DBConnection.getConnection();
			// SQL문 작성
			sql = "UPDATE salary SET pay=? WHERE name=?";
			// JDBC수행 3단계
			pstmt = conn.prepareStatement(sql);
			// SQL문의 ?에 데이터 할당
			pstmt.setInt(1, pay);
			pstmt.setString(2, name);
			// JDBC수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 수정");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, pstmt, conn);
		}

	}
}
