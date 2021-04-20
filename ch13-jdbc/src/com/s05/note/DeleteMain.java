package com.s05.note;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.s02.preparedstatement.DBConnection;

public class DeleteMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("글 번호 : ");
			int num = Integer.parseInt(br.readLine());

			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();
			// SQL문 작성
			sql = "DELETE FROM note WHERE num=?";

			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// SQL문의 ?에 데이터를 할당
			pstmt.setInt(1, num);

			// JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 삭제했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(null, pstmt, conn);
		}
	}
}
