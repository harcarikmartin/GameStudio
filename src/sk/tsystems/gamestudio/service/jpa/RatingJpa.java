package sk.tsystems.gamestudio.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.RatingJ;
import sk.tsystems.gamestudio.service.RatingService;

public class RatingJpa implements RatingService{

	@Override
	public void add(RatingJ rating) {
		if(isRatingPresent(rating)) {
			deleteRating(rating);
		}
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(rating);
		JpaHelper.commitTransaction();	
	}

	@Override
	public int findRatingsCountForGame(String gameName) {
		return Math.toIntExact((long) JpaHelper.getEntityManager().createQuery("Select count(r.gameName) from RatingJ r where r.gameName = :gameName").setParameter("gameName", gameName).getSingleResult());
	}

	@Override
	public double findAverageRatingForGame(String gameName) {
			Object o = JpaHelper.getEntityManager().createQuery("Select avg(cast(r.rating as double)) from RatingJ r where r.gameName = :gamename").setParameter("gamename", gameName).getSingleResult();
			Double d = Double.parseDouble(o.toString());
			return d;
	}
	
	private void deleteRating(RatingJ rating) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
//		em.remove(rating);
		Query query = em.createQuery("delete from RatingJ r where r.gameName = :gameName and r.playerName = :playerName");
		query.setParameter("gameName", rating.getGameName());
		query.setParameter("playerName", rating.getPlayerName());
		query.executeUpdate();
		JpaHelper.commitTransaction();
	}
	
	private boolean isRatingPresent(RatingJ rating) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(r.gameName) from RatingJ r where r.gameName = :gameName and r.playerName = :playerName");
		query.setParameter("gameName", rating.getGameName());
		query.setParameter("playerName", rating.getPlayerName());
		Object result = query.getSingleResult();
		if(Math.toIntExact((long)result) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
