package com.s05.note;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.s02.preparedstatement.DBConnection;

public class UpdateMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("글번호 : ");
			// String -> int
			int num = Integer.parseInt(br.readLine());
			System.out.print("이름 : ");
			String name = br.readLine();
			System.out.print("비밀번호 : ");
			String passwd = br.readLine();
			System.out.print("제목 : ");
			String title = br.readLine();
			System.out.print("내용 : ");
			String content = br.readLine();
			System.out.print("이메일 : ");
			String email = br.readLine();

			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();

			// SQL문 작성
			sql = "UPDATE note SET name=?,passwd=?,title=?," + "content=?,email=? WHERE num=?";

			// JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// SQL문의 ?에 데이터 할당
			pstmt.setString(1, name);
			pstmt.setString(2, passwd);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, email);
			pstmt.setInt(6, num);

			// JDBC 수행 4단계 : SQL문 실행
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 수정했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(null, pstmt, conn);
		}
	}
}
