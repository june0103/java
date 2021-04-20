package com.s05.note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s02.preparedstatement.DBConnection;

public class SelectMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "SELECT * FROM note ORDER BY num DESC";

			// JDBC 수행 3단계 : PreparedStatement 객체
			pstmt = conn.prepareStatement(sql);

			System.out.println("글번호\t제목\t이름\t작성일");
			// JDBC 수행 4단계 : SQL문을 실행해서 결과집합을 구하고
			// 결과집합을 ResultSet에 담아서 반환
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("num"));
				System.out.print("\t");
				System.out.print(rs.getString("title"));
				System.out.print("\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t");
				System.out.println(rs.getDate("reg_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(rs, pstmt, conn);
		}
	}
}
