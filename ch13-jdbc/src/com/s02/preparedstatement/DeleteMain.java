package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			// JDBC ���� 1�ܰ� : ����̹� �ε�
			// JDBC ���� 2�ܰ� : connection ��ü ����()
			conn = DBConnection.getConnection();

			// SQL�� �ۼ�
			sql = "DELETE FROM test2 WHERE num=?";

			// JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, 1);

			int count = psmt.executeUpdate();
			System.out.println(count + "�� ���� �����߽��ϴ�.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, psmt, conn);
		}
	}

}
