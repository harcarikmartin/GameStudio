package sk.tsystems.gamestudio.service.jpa;

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
		return 0;
		//Integer.parseInt( JpaHelper.getEntityManager().createQuery("Select count(r.gameName) from RatingJ r where r.gameName = :gameName").setParameter("gameName", gameName).getSingleResult().toString());
	}

	
	public double findAverageRatingForGame(Game game) {
		return 0;
//			Object o = JpaHelper.getEntityManager().createQuery("Select avg(cast(r.rating as double)) from RatingJ r where r.gameName = :gamename").setParameter("gamename", gameName).getSingleResult();
//			Double d = Double.parseDouble(o.toString());
			
	}
}
