package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InserMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			// JDBC ���� 1�ܰ� : ����̹� �ε�
			// JDBC ���� 2�ܰ� : connection ��ü ����()
			conn = DBConnection.getConnection();

			// SQL�� �ۼ�
			sql = "INSERT INTO test2 (num,title,name,memo,email,reg_date) "
					+ "VALUES(test2_seq.nextval, ?,?,?,?,SYSDATE)";

			// JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			psmt = conn.prepareStatement(sql);

			// ?�� �����͸� ����
			psmt.setString(1, "�������"); // title
			psmt.setString(2, "��ĥ��"); // name
			psmt.setString(3, "���� ���Գ�"); // memo
			psmt.setString(4, "test2@test.com"); // email

			// JDBC ���� 4�ܰ� : SQL�� ����
			int count = psmt.executeUpdate();
			System.out.println(count + "���� ���� �߰��߽��ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, psmt, conn);
		}
	}
}
