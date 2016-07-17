package sk.tsystems.gamestudio.service.jpa;

import java.util.List;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;

public class ScoreJpa implements ScoreService{

	@Override
	public void add(Score score) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(score);
		JpaHelper.commitTransaction();
	}

	@Override
	public List<Score> findTenBestScoresForGame(String game) {
		return JpaHelper.getEntityManager().createQuery("Select s from Score s where s.gameName = :gameName").setParameter("gameName", game).getResultList();
		
	}

}
