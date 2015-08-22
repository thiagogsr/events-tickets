package eventstickets.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainDAO {
	public EntityManager openSession() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("eventstickets.jpa");
		return factory.createEntityManager();
	}
}