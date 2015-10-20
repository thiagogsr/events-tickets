package eventstickets.dao;

import javax.persistence.EntityManager;

import eventstickets.models.Talk;

public class TalkDAO extends MainDAO{

	public boolean create (Talk talk){
		EntityManager manager = openSession();
		try{
			manager.getTransaction().begin();
			manager.persist(talk);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e){
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
}
