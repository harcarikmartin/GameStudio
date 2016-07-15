package sk.tsystems.gamestudio.service;

import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.jdbc.ScoreJDBC;

public class ScoreListing {
	
	public ScoreListing(String gameName) {
		List<Score> scores = new ScoreJDBC().findTenBestScoresForGame(gameName);
		for(Score score : scores) {
			System.out.println(score.getPlayerName() + ", " + score.getScore());
		}
	}
}
