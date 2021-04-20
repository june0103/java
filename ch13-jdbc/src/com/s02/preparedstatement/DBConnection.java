package com.s02.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_ID = "scott";
	private static final String DB_PASSWORD = "tiger";

	// Connection ����
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		// JDBC ���� 1�ܰ� : ����̹� �ε�
		Class.forName(DB_DRIVER);

		// JDBC ���� 2�ܰ� : connection ��ü ����(ID, ��й�ȣ ����)
		return DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);
	}

	// �ڿ�����
	public static void executeClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}

}
