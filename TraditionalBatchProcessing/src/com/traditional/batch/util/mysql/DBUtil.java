package com.traditional.batch.util.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getConnection() throws Exception {

		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch", "batchuser", "batch");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return connection;

	}

}
