package sk.tsystems.gamestudio.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.service.CommentService;

public class CommentJDBC implements CommentService{
	public static final String ADD_COMMENT = "INSERT INTO comments (comments, id_game, id_player) VALUES (?, ?, ?)";
	
	NameToId id = new NameToId();
	
	@Override
	public void add(Comment comment) {
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
	public List<Comment> findCommentsForGame(String game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findCommentForGame(String game, String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
