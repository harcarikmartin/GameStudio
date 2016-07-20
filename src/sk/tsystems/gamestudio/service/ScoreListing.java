package sk.tsystems.gamestudio.service;

import java.util.List;

import sk.tsystems.gamestudio.entity.ScoreJ;
import sk.tsystems.gamestudio.service.jdbc.ScoreJDBC;
import sk.tsystems.gamestudio.service.jpa.ScoreJpa;

public class ScoreListing {
	private List<ScoreJ> scores;
	
	public ScoreListing(String gameName) {
		scores = new ScoreJpa().findTenBestScoresForGame(gameName);
//		scores = new ScoreJDBC().findTenBestScoresForGame(gameName);
		}
	
	public void print() {
		for(int i = 0; i < scores.size(); i++) {
			System.out.println(scores.get(i).getPlayerName() + ", " + scores.get(i).getScore());
		}
	}
}
