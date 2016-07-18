package sk.tsystems.gamestudio.service;

import java.util.List;
import sk.tsystems.gamestudio.entity.CommentJ;

public interface CommentService {
	void add(CommentJ comment);
	
	List<CommentJ> findCommentsForGame(String game);
}