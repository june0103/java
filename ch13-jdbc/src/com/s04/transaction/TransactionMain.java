package com.s04.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.s02.preparedstatement.DBConnection;

public class TransactionMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;

		try {
			// JDBC수행 1,2단계
			conn = DBConnection.getConnection();

			// 트랜잭션 처리를 위해서 auto commit 해제
			conn.setAutoCommit(false);

			// SQL문 작성
			sql = "INSERT INTO test1 VALUES ('Korea',100)";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.executeUpdate();

			sql = "INSERT INTO test1 VALUES ('England',200)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.executeUpdate();

			// 테스트용으로 오류가 있는 SQL문 작성
			sql = "INSERT INTO test1 VALUES ('China',300";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.executeUpdate();

			// 정상적으로 작업이 완료되면 commit
			conn.commit();

			System.out.println("작업 완료!!");
		} catch (Exception e) {
			e.printStackTrace();
			// 예외가 발생하면 rollback
			try {
				conn.rollback();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		} finally {
			// 자원정리
			DBConnection.executeClose(null, pstmt3, null);
			DBConnection.executeClose(null, pstmt2, null);
			DBConnection.executeClose(null, pstmt1, conn);
		}
	}
}
