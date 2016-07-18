package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.CommentJ;
import sk.tsystems.gamestudio.service.CommentService;

public class CommentJDBC implements CommentService{
	public static final String ADD_COMMENT = "INSERT INTO comments (id, comments, id_game, id_player) VALUES (nextval('id'), ?, ?, ?)";
	public static final String GET_COMMENTS = "select comments from comments where id_game = ?";
	
	NameToId id = new NameToId();
	
	@Override
	public void add(CommentJ comment) {
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(ADD_COMMENT)) {
				stmt.setString(1, comment.getComment());
				stmt.setInt(2, id.getGameId(comment.getGameName()));
				stmt.setInt(3, id.getPlayerId(comment.getPlayerName()));
				stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommentJ> findCommentsForGame(String game) {
		List<CommentJ> comments = new ArrayList<>();
		try (Connection c = new DBConnection().connectToDB();
				PreparedStatement stmt = c.prepareStatement(GET_COMMENTS)) {
				stmt.setInt(1, id.getGameId(game));
				try (ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						comments.add(new CommentJ(rs.getString(1)));
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

}
