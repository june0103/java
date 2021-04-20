package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InserMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			// JDBC 수행 1단계 : 드라이버 로드
			// JDBC 수행 2단계 : connection 객체 생성()
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "INSERT INTO test2 (num,title,name,memo,email,reg_date) "
					+ "VALUES(test2_seq.nextval, ?,?,?,?,SYSDATE)";

			// JDBC 수행 3단계 : PreparedStatement 객체 생성
			psmt = conn.prepareStatement(sql);

			// ?에 데이터를 연결
			psmt.setString(1, "배고프다"); // title
			psmt.setString(2, "땡칠이"); // name
			psmt.setString(3, "오늘 뭐먹냐"); // memo
			psmt.setString(4, "test2@test.com"); // email

			// JDBC 수행 4단계 : SQL문 실행
			int count = psmt.executeUpdate();
			System.out.println(count + "개의 행을 추가했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, psmt, conn);
		}
	}
}
