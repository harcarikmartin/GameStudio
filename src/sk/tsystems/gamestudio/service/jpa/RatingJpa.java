package sk.tsystems.gamestudio.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.jpa.Game;
import sk.tsystems.gamestudio.entity.jpa.Rating;

public class RatingJpa {
	
	public void addRating(Rating rating) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(rating);
		JpaHelper.commitTransaction();
	}
	
	public int findRatingsCountForGame(Game game) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select count(r) from Rating r where r.game = :game");
		query.setParameter("game", game);
		if(query.getResultList().isEmpty()) {
			return 0;
		} else {
			return Math.toIntExact((long)query.getResultList().get(0));
		}
	}
	
	public double findAverageRatingForGame(Game game) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select avg(r.rating) from Rating r where r.game = :game");
		query.setParameter("game", game);
		if(query.getResultList().isEmpty()) {
			return 0;
		} else {
			return (double) query.getResultList().get(0);
		}
	}
}
