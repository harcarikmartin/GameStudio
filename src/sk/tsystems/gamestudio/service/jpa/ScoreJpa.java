package sk.tsystems.gamestudio.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.jpa.Game;
import sk.tsystems.gamestudio.entity.jpa.Score;

public class ScoreJpa {
	
	public void addScore(Score score) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(score);
		JpaHelper.commitTransaction();
	}
	
	public List<Score> findTenBestScoresForGame(Game game) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select s from Score s where s.gameName = :gameName");
		query.setParameter("gameName", game);
		query.getResultList();
		return null;
	}
}
