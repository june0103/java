package com.s03.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.s02.preparedstatement.DBConnection;

public class CallableStatementMain {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = null;

		String name = "Kim";
		float rate = 0.03f;

		try {
			// JDBC수행 1,2단계
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "{call adjust(?,?)}";

			// JDBC수행 3단계 : CallableStatement 객체 생성
			cstmt = conn.prepareCall(sql);
			// ?에 데이터 연결
			cstmt.setString(1, name);// n
			cstmt.setFloat(2, rate);// rate

			// JDBC수행 4단계 : 프로시저 호출
			cstmt.executeUpdate();
			System.out.println("급여 정보를 수정했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			if (cstmt != null)
				try {
					cstmt.close();
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
