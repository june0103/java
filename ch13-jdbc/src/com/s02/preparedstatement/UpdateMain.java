package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			// JDBC ���� 1�ܰ� : ����̹� �ε�
			// JDBC ���� 2�ܰ� : connection ��ü ����()
			conn = DBConnection.getConnection();

			// SQL�� �ۼ�
			sql = "UPDATE test2 SET title=?, name=?, memo=?, email=? WHERE num=?";

			// JDBC ���� 3�ܰ� : PreparedStatement ��ü ����
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, "����� ����");
			psmt.setString(2, "��ȣ��");
			psmt.setString(3, "������ 14��");
			psmt.setString(4, "sample@sapmle.com");
			psmt.setInt(5, 1);

			int count = psmt.executeUpdate();
			System.out.println(count + "�� ���� ������ �����߽��ϴ�.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.executeClose(null, psmt, conn);
		}

	}

}
