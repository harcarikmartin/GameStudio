package sk.tsystems.gamestudio.service.jpa;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.RatingJ;
import sk.tsystems.gamestudio.service.RatingService;

public class RatingJpa implements RatingService{

	@Override
	public void add(RatingJ rating) {
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
}
