package sk.tsystems.gamestudio.service.jpasimple;

import java.util.List;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.ScoreJ;
import sk.tsystems.gamestudio.service.ScoreService;

public class ScoreJpa implements ScoreService{

	@Override
	public void add(ScoreJ score) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(score);
		JpaHelper.commitTransaction();
	}

	@Override
	public List<ScoreJ> findTenBestScoresForGame(String game) {
		return JpaHelper.getEntityManager().createQuery("Select s from ScoreJ s where s.gameName = :gameName").setParameter("gameName", game).getResultList();
		
	}

}
