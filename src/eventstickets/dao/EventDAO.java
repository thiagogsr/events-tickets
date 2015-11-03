package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import eventstickets.models.Event;
import eventstickets.models.User;


public class EventDAO extends MainDAO{

	public boolean create (Event event, User currentUser){
		EntityManager manager = openSession();
		try{
			manager.getTransaction().begin();
			event.setCreatedBy(currentUser);
			event.setUpdatedAt(new Date());
			manager.persist(event);
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
			Event event = manager.find(Event.class, id);
			manager.remove(manager.contains(event) ? event : manager.merge(event));
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public boolean update(Event event) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			event.setUpdatedAt(new Date());
		    manager.merge(event);
		    manager.getTransaction().commit();
		    return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Event find(Integer id) {
		EntityManager manager = openSession();
		return manager.find(Event.class, id);
	}
	
	public List<Event> all(){
		EntityManager manager = openSession();
		List<Event> events = manager.createQuery("from eventstickets.models.Event").getResultList();
		return events;
	}
	
}