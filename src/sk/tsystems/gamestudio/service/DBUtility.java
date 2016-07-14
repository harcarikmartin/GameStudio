package sk.tsystems.gamestudio.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
	private static final String USER = "GameCenter";
	private static final String PASSWORD = "Gamecenter";
	
	public Connection connectToDB() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB connected");
			return c;
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			System.err.println("Error code: " + e.getErrorCode());
		}
		System.out.println("DB connection failed.");
		return c;
	}
	
//	public static final String ADD_RATING = "INSERT INTO register (id, name, phone_number) VALUES (ids.nextval, ?, ?)";
//	public static final String GET_RATING_COUNT = "SELECT COUNT(*) FROM rating WHERE ID_GAME IS ?";
//	public static final String GET_AVERAGE_RATING = "SELECT COUNT(*) FROM register";
}
