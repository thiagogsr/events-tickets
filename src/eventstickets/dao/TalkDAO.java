package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import eventstickets.models.Talk;
import eventstickets.models.User;

public class TalkDAO extends MainDAO {
	public boolean create(Talk talk, User currentUser) {
		EntityManager manager = openSession();
		try{
			manager.getTransaction().begin();
			talk.setCreatedBy(currentUser);
			talk.setCreatedAt(new Date());
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

	public boolean destroy(Integer id) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			Talk talk = manager.find(Talk.class, id);
			manager.remove(manager.contains(talk) ? talk : manager.merge(talk));
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public boolean update(Talk talk) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
		    manager.merge(talk);
		    manager.getTransaction().commit();
		    return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Talk find(Integer id) {
		EntityManager manager = openSession();
		return manager.find(Talk.class, id);
	}
	
	public List<Talk> all(){
		EntityManager manager = openSession();
		List<Talk> talks = manager.createQuery("from eventstickets.models.Talk").getResultList();
		return talks;
	}
}
