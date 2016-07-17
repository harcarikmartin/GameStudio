package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;

public class ScoreJDBC implements ScoreService{
	public static final String ADD_SCORE = "INSERT INTO score (id, score, id_game, id_player) VALUES (nextval('id'), ?, ?, ?)";
	public static final String GET_BEST_SCORES = "SELECT score, p.name, g.name from score s join player p on s.id_player = p.id join game g on s.id_game = g.id  where g.name = ? order by score desc";
	
	NameToId id = new NameToId();
	
	@Override
	public void add(Score score) {
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(ADD_SCORE)) {
				stmt.setInt(1, score.getScore());
				stmt.setInt(2, id.getGameId(score.getGameName()));
				stmt.setInt(3, id.getPlayerId(score.getPlayerName()));
				stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Score> findTenBestScoresForGame(String game) {
		List<Score> scores = new ArrayList<>();
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(GET_BEST_SCORES)) {
				stmt.setString(1, game);
				try (ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						scores.add(new Score(rs.getInt(1), rs.getString(2), rs.getString(3)));
					}
				}
				return scores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scores;
	}
	
}
