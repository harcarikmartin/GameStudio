package sk.tsystems.gamestudio.service.jpa;

import java.util.List;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.ScoreJ;
import sk.tsystems.gamestudio.entity.jpa.Game;
import sk.tsystems.gamestudio.entity.jpa.Score;

public class ScoreJpa {
	
	public void addScore(Score score) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(score);
		JpaHelper.commitTransaction();
	}
	
	public List<ScoreJ> findTenBestScoresForGame(Game game) {
		return null;
//		JpaHelper.getEntityManager().createQuery("Select s from ScoreJ s where s.gameName = :gameName").setParameter("gameName", game).getResultList();
		
	}
}
