package sk.tsystems.gamestudio.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.jpa.Player;

public class PlayerJpa {
	
	public Player setPresentPlayer(String playerName) {
		int id = getId(playerName);
		if(id > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Player.class, id);
		} else {
			return new Player(playerName);
		}
	}

	private int getId(String playerName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select id from Player p where p.playerName = :playerName");
		query.setParameter("playerName", playerName);
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			System.out.println((int) query.getResultList().get(0));
			return (int) query.getResultList().get(0);
		}
	}
	
	
}
