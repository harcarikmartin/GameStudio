package sk.tsystems.gamestudio.service;

import sk.tsystems.gamestudio.entity.RatingJ;

public interface RatingService {
	void add(RatingJ rating);
	
	int findRatingsCountForGame(String gameName);
	
	double findAverageRatingForGame(String gameName);
}