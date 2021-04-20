package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;
		ResultSet rs = null;

		try {
			// JDBC 수행 1단계 : 드라이버 로드
			// JDBC 수행 2단계 : connection 객체 생성()
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "SELECT * FROM test2 ORDER BY num DESC";

			// JDBC 수행 3단계 : PreparedStatement 객체 생성
			psmt = conn.prepareStatement(sql);

			// JDBC 수행 4단계 : SQL문 실행해서 결과집합을 ResultSet에 보관하고 ResultSet객체 반환
			rs = psmt.executeQuery();

			System.out.println("번호\t\t제목\t\t작성자\t\t이메일\t\t날짜\t\t내용");

			while (rs.next()) {
				System.out.print(rs.getInt("num"));
				System.out.print("\t\t");
				System.out.print(rs.getString("title"));
				System.out.print("\t\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t\t");
				System.out.print(rs.getString("email"));
				System.out.print("\t");
				System.out.print(rs.getDate("reg_date"));
				System.out.print("\t");
				System.out.println(rs.getString("memo"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(rs, psmt, conn);
		}

	}
}
