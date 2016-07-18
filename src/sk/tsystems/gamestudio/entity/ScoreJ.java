package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ScoreJ {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private int score;
	
	private String playerName;
	
	private String gameName;
	
	public ScoreJ(int score, String playerName, String gameName) {
		this.setScore(score);
		this.setPlayerName(playerName);
		this.setGameName(gameName);
	}
	
	public ScoreJ(int score, String playerName) {
		
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

	@Override
	public String toString() {
		return "Score [ident=" + ident + ", score=" + score + ", playerName=" + playerName + ", gameName=" + gameName
				+ "]";
	}
	
	
}
