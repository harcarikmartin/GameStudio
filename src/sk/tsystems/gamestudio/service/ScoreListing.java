package sk.tsystems.gamestudio.service;

import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.jdbc.ScoreJDBC;

public class ScoreListing {
	private List<Score> scores;
	
	public ScoreListing(String gameName) {
		scores = new ScoreJDBC().findTenBestScoresForGame(gameName);
		}
	
	public void print() {
		for(int i = 0; i < scores.size(); i++) {
			System.out.println(scores.get(i).getPlayerName() + ", " + scores.get(i).getScore());
		}
	}
}
