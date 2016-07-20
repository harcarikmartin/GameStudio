package sk.tsystems.gamestudio.service;

import java.util.List;
import sk.tsystems.gamestudio.entity.ScoreJ;

public interface ScoreService {
	void add(ScoreJ score);
	
	List<ScoreJ> findTenBestScoresForGame(String game);
}