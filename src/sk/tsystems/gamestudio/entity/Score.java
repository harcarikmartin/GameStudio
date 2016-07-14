package sk.tsystems.gamestudio.entity;

public class Score {
	private int score;
	private String playerName;
	private String gameName;
	
	public Score(int score, String playerName, String gameName) {
		this.setScore(score);
		this.setPlayerName(playerName);
		this.setGameName(gameName);
	}
	
	public Score(int score, String playerName) {
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
	
	
}
