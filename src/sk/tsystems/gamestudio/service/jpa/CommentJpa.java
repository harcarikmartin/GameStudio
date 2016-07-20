package sk.tsystems.gamestudio.service.jpa;

import java.util.List;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.jpa.Comment;
import sk.tsystems.gamestudio.entity.jpa.Game;

public class CommentJpa {
	
	public void addComment(Comment comment) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(comment);
		JpaHelper.commitTransaction();
	}
	
	public List<Comment> findCommentsForGame(Game game) {
		return null; 
		//JpaHelper.getEntityManager().createQuery("Select c from CommentJ c where c.gameName = :gameName").setParameter("gameName", game).getResultList();
	}
}
