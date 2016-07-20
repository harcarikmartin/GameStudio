package sk.tsystems.gamestudio.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.jpa.Player;

public class PlayerJpa {
	
	public Player setPresentPlayer(String playerName) {
		if(getId(playerName) > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Player.class, playerName);
		} else {
			return new Player(playerName);
		}
	}

	private int getId(String playerName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select id from Player p where p.playerName = :playerName");
		query.setParameter("playerName", playerName);
		if(query.getResultList().isEmpty()) {
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}
	
	
}
