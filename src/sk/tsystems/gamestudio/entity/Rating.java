package sk.tsystems.gamestudio.entity;

public class Rating {
	private String rating;
	private String playerName;
	private String gameName;
	
	public Rating(String rating, String playerName, String gameName) {
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}
