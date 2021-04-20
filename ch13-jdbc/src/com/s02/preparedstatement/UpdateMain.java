package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			// JDBC 수행 1단계 : 드라이버 로드
			// JDBC 수행 2단계 : connection 객체 생성()
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "UPDATE test2 SET title=?, name=?, memo=?, email=? WHERE num=?";

			// JDBC 수행 3단계 : PreparedStatement 객체 생성
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, "여기는 강남");
			psmt.setString(2, "강호동");
			psmt.setString(3, "지금은 14시");
			psmt.setString(4, "sample@sapmle.com");
			psmt.setInt(5, 1);

			int count = psmt.executeUpdate();
			System.out.println(count + "개 행의 정보를 수정했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, psmt, conn);
		}

	}

}
