package sk.tsystems.gamestudio.service.sorm;

import sk.tsystems.gamestudio.entity.RatingJ;
import sk.tsystems.gamestudio.service.RatingService;

public class RatingSorm implements RatingService{

	@Override
	public void add(RatingJ rating) {
	
	}

	@Override
	public int findRatingsCountForGame(String gameName) {
		return 0;
	}

	@Override
	public double findAverageRatingForGame(String gameName) {
		return 0;
	}
}
