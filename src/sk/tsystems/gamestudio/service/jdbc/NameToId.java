package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NameToId {
	public static final String GET_GAME_ID = "SELECT id from game where name = ?";
	public static final String GET_PLAYER_ID = "SELECT id from player where name = ?";
	
	public int find(String parameter, String query) {
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(query)) {
				stmt.setString(1, parameter);
				try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
//					System.out.println("Id is" + rs.getInt(1));
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getGameId(String gameName) {
		return find(gameName, GET_GAME_ID);
	}
	
	public int getPlayerId(String playerName) {
		return find(playerName, GET_PLAYER_ID);
	}
}
