package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommentJ {
	@Id
	@GeneratedValue
	private int ident;
	
	private String comment;
	private String playerName;
	private String gameName;
	
	public CommentJ() {
		super();
	}

	public CommentJ(String comment, String playerName, String gameName) {
		this.setComment(comment);
		this.setPlayerName(playerName);
		this.setGameName(gameName);
	}
	
	public CommentJ(String comment) {
		this(comment, null, null);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public String toString() {
		return "Comment [ident=" + ident + ", comment=" + comment + ", playerName=" + playerName + ", gameName="
				+ gameName + "]";
	}
	
	
}
