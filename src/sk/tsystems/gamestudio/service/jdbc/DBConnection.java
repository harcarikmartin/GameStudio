package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
//	private static final String USER = "GameCenter";
//	private static final String PASSWORD = "Gamecenter";

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "Gamecenter";
	private static final String PASSWORD = "Gamecenter";
	
	public Connection connectToDB() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB connected");
			return c;
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			System.err.println("Error code: " + e.getErrorCode());
		}
		System.out.println("DB connection failed.");
		return c;
	}
}
