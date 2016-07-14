package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.service.RatingService;

public class RatingJDBC implements RatingService {
	public static final String ADD_RATING = "INSERT INTO rating (rating, id_game, id_player) VALUES (?, ?, ?)";
	public static final String GET_RATING_COUNT = "SELECT COUNT(*) FROM rating WHERE id_game = ?";
	public static final String GET_AVERAGE_RATING = "SELECT AVG(*) FROM rating where id_game = ?";
	
	Connection c = new DBConnection().connectToDB();
	NameToId id = new NameToId();
	
//	public static void main(String[] args) {
//		
//		if(conn != null) {
//			System.out.println("success");
//		} else {
//			System.out.println("fail");
//		}
//	}
	
	@Override
	public void add(Rating rating) {
		try (PreparedStatement stmt = c.prepareStatement(ADD_RATING)) {
				stmt.setString(1, rating.getRating());
				stmt.setInt(2, id.getGameId(rating.getGameName()));
				stmt.setInt(3, id.getPlayerId(rating.getPlayerName()));
				stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int ratingsCountForGame(String gameName) {
		try (PreparedStatement stmt = c.prepareStatement(GET_RATING_COUNT)) {
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
	public List<Rating> findAverageRatingForGame(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
