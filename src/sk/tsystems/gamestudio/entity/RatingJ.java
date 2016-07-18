package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RatingJ {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private int rating;
	
	private String playerName;
	
	private String gameName;
	
	public RatingJ() {
		super();
	}

	public RatingJ(int rating, String playerName, String gameName) {
		this.setRating(rating);
		this.setPlayerName(playerName);
		this.setGameName(gameName);
	}
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Rating [ident=" + ident + ", rating=" + rating + ", playerName=" + playerName + ", gameName=" + gameName
				+ "]";
	}
	
	
}
