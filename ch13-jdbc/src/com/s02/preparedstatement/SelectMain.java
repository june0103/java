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
			// JDBC ���� 1�ܰ� : ����̹� �ε�
			// JDBC ���� 2�ܰ� : connection ��ü ����()
			conn = DBConnection.getConnection();

			// SQL�� �ۼ�
			sql = "SELECT * FROM test2 ORDER BY num DESC";

			// JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			psmt = conn.prepareStatement(sql);

			// JDBC ���� 4�ܰ� : SQL�� �����ؼ� ��������� ResultSet�� �����ϰ� ResultSet��ü ��ȯ
			rs = psmt.executeQuery();

			System.out.println("��ȣ\t\t����\t\t�ۼ���\t\t�̸���\t\t��¥\t\t����");

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
