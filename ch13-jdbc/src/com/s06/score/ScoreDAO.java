package com.s06.score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.s02.preparedstatement.DBConnection;

public class ScoreDAO {
	// 성적입력
	public void insertInfo(ScoreVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();

			// SQL문 작성
			// 테이블명 : score
			// 컬럼명 : num,name,korean,english,math,total,avg,grade,reg_date
			// score_seq.nextval SYSDATE
			sql = "INSERT INTO score (num,name,korean,english," + "math,total,avg,grade,reg_date) VALUES "
					+ "(score_seq.nextval,?,?,?,?,?,?,?,SYSDATE)";

			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// SQL문의 ?에 데이터 할당
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKorean());
			pstmt.setInt(3, vo.getEnglish());
			pstmt.setInt(4, vo.getMath());
			pstmt.setInt(5, vo.makeTotal());
			pstmt.setInt(6, vo.makeAvg());
			pstmt.setString(7, vo.makeGrade());

			// JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행이 삽입되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(null, pstmt, conn);
		}
	}

	// 목록보기
	public void selectInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();
			// SQL문 작성(num DESC)
			sql = "SELECT * FROM score ORDER BY num DESC";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// JDBC 수행 4단계
			rs = pstmt.executeQuery();

			System.out.println("---------------------------");
			System.out.println("번호 이름 국어 영어 수학 총점 평균 등급 등록일");
			// while을 이용한 반복 및 출력
			// num,name,korean,english,math,total,avg,grade,reg_date
			while (rs.next()) {
				System.out.print(rs.getInt("num"));
				System.out.print(" ");
				System.out.print(rs.getString("name"));
				System.out.print(" ");
				System.out.print(rs.getInt("korean"));
				System.out.print(" ");
				System.out.print(rs.getInt("english"));
				System.out.print(" ");
				System.out.print(rs.getInt("math"));
				System.out.print(" ");
				System.out.print(rs.getInt("total"));
				System.out.print(" ");
				System.out.print(rs.getInt("avg"));
				System.out.print(" ");
				System.out.print(rs.getString("grade"));
				System.out.print(" ");
				System.out.println(rs.getDate("reg_date"));
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(rs, pstmt, conn);
		}

	}

	// 상세정보
	public void selectDetailInfo(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();
			// SQL문 작성
			sql = "SELECT * FROM score WHERE num=?";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// SQL문의 ?에 데이터 할당
			pstmt.setInt(1, num);
			// JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("번호 : " + rs.getInt("num"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("국어 : " + rs.getInt("korean"));
				System.out.println("영어 : " + rs.getInt("english"));
				System.out.println("수학 : " + rs.getInt("math"));
				System.out.println("총점 : " + rs.getInt("total"));
				System.out.println("평균 : " + rs.getInt("avg"));
				System.out.println("등급 : " + rs.getString("grade"));
				System.out.println("등록일 : " + rs.getDate("reg_date"));
			} else {// 행이 없는 경우
				System.out.println("검색된 행이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			DBConnection.executeClose(rs, pstmt, conn);
		}
	}

	// 성적 수정
	public void updateInfo(ScoreVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {

			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();
			// SQL문 작성
			sql = "UPDATE score SET name=?,korean=?,english=?,math=?,total=?,avg=?,grade=? WHERE num=?";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// SQL문에 ?에 데이터 할당
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKorean());
			pstmt.setInt(3, vo.getEnglish());
			pstmt.setInt(4, vo.getMath());
			pstmt.setInt(5, vo.makeTotal());
			pstmt.setInt(6, vo.makeAvg());
			pstmt.setString(7, vo.makeGrade());
			pstmt.setInt(8, vo.getNum());

			// JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행이 수정되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, pstmt, conn);
		}
	}

	// 성적 삭제
	public void deleteInfo(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {

			// JDBC 수행 1,2단계
			conn = DBConnection.getConnection();
			// SQL문 작성
			sql = "DELETE FROM score WHERE num=?";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// SQL문에 ?에 데이터 할당
			pstmt.setInt(1, num);

			// JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행이 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, pstmt, conn);
		}
	}
}
