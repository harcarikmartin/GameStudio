package sk.tsystems.gamestudio.service.jpa;

import sk.ness.jpa.JpaHelper;
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
		return JpaHelper.getEntityManager().createQuery("Select Count(*) from Rating r where r.gameName = :gameName").setParameter("gameName", gameName).getFirstResult();
	}

	@Override
	public double findAverageRatingForGame(String gameName) {
		return JpaHelper.getEntityManager().createQuery("Select avg(r.rating) from Rating r where r.gameName = :gameName").setParameter("gameName", gameName).getFirstResult();
	}

}
