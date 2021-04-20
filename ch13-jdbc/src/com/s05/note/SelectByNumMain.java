package com.s05.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s02.preparedstatement.DBConnection;

public class SelectByNumMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("글번호 : ");
			// String -> int
			int num = Integer.parseInt(br.readLine());

			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "SELECT * FROM note WHERE num=?";

			// JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 할당
			pstmt.setInt(1, num);

			// JDBC 수행 4단계 : SQL문을 실행해서 1개의 행을
			// ResultSet에 담아서 반환
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("글번호 : " + rs.getInt("num"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("passwd"));
				System.out.println("제목 : " + rs.getString("title"));
				System.out.println("내용 : " + rs.getString("content"));
				System.out.println("이메일 : " + rs.getString("email"));
				System.out.println("작성일 : " + rs.getDate("reg_date"));
			} else {// 행이 없는 경우
				System.out.println("검색된 정보가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(rs, pstmt, conn);
			// BufferedReader 자원정리
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}
	}
}
