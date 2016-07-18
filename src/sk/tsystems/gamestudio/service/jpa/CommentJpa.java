package sk.tsystems.gamestudio.service.jpa;

import java.util.List;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.CommentJ;
import sk.tsystems.gamestudio.service.CommentService;

public class CommentJpa implements CommentService {

	@Override
	public void add(CommentJ comment) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(comment);
		JpaHelper.commitTransaction();
		
	}

	@Override
	public List<CommentJ> findCommentsForGame(String game) {
		return JpaHelper.getEntityManager().createQuery("Select c from Comments c where c.gameName = :gameName").setParameter("gameName", game).getResultList();
	}
	
}
