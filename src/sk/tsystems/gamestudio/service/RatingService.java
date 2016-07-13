package sk.tsystems.gamestudio.service;

import java.util.List;
import sk.tsystems.gamestudio.entity.Rating;

public interface RatingService {
	void add(Rating rating);
	
	List<Rating> findRatingForGame(String game);
}