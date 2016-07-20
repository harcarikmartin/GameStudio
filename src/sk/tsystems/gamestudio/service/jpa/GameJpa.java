package sk.tsystems.gamestudio.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.jpa.Game;

public class GameJpa {
	
	public Game setPresentGame(String gameName) {
		if(getId(gameName) > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Game.class, gameName);
		} else {
			return new Game(gameName);
		}
	}

	private int getId(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select id from Game g where g.gameName = :gameName");
		query.setParameter("gameName", gameName);
		if(query.getResultList().isEmpty()) {
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}
}
