package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.service.RatingService;

public class RatingJDBC implements RatingService {
	public static final String ADD_RATING = "INSERT INTO rating (rating, id_game, id_player) VALUES (?, ?, ?)";
	public static final String GET_RATING_COUNT = "SELECT COUNT(*) FROM rating WHERE id_game = ?";
	public static final String GET_AVERAGE_RATING = "SELECT AVG(rating) FROM rating where id_game = ?";
	public static final String RATING_OCCURS = "SELECT Count(*) FROM rating where id_game = ? AND id_player = ?";
	public static final String DELETE_RATING = "DELETE FROM rating where id_game = ? AND id_player = ?";
	
	NameToId id = new NameToId();
	
	@Override
	public void add(Rating rating) {
		int gameId = id.getGameId(rating.getGameName());
		int playerId = id.getPlayerId(rating.getPlayerName());
//		System.out.println("GameId = " + gameId + " PlayerId = " + playerId);
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement st = c.prepareStatement(RATING_OCCURS)) {
				st.setInt(1, gameId);
				st.setInt(2, playerId);
				try (ResultSet rs = st.executeQuery()) {
					if(rs.next()) {
						try (PreparedStatement stm = c.prepareStatement(DELETE_RATING)) {
							stm.setInt(1, gameId);
							stm.setInt(2, playerId);
							stm.executeUpdate();
						}
					} 
					try (PreparedStatement stmt = c.prepareStatement(ADD_RATING)) {
						stmt.setInt(1, Integer.parseInt(rating.getRating()));
						stmt.setInt(2, gameId);
						stmt.setInt(3, playerId);
						stmt.executeUpdate();
					}
				}
		} catch (SQLException e1) {
			System.err.println("Error while adding rating: ");
			e1.printStackTrace();
		}
		
	}

	@Override
	public int findRatingsCountForGame(String gameName) {
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(GET_RATING_COUNT)) {
				stmt.setInt(1, id.getGameId(gameName));
				try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double findAverageRatingForGame(String gameName) {
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(GET_AVERAGE_RATING)) {
				stmt.setInt(1, id.getGameId(gameName));
				try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
