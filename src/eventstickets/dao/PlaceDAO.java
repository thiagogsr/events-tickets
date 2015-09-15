package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import eventstickets.dao.MainDAO;
import eventstickets.models.Place;
import eventstickets.models.User;

public class PlaceDAO extends MainDAO {
	public List<Place> all() {
		EntityManager manager = openSession();
		List<Place> places = manager.createQuery("from eventstickets.models.Place").getResultList();
		return places;
	}
	
	public boolean create(Place place, User currentUser) {
		EntityManager manager = openSession();
		try {
			place.setCreatedBy(currentUser);
			place.setCreatedAt(new Date());
		
		    manager.getTransaction().begin();    
		    manager.persist(place);
		    manager.getTransaction().commit();
		    return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public boolean update(Place place) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();    
		    manager.merge(place);
		    manager.getTransaction().commit();
		    return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Place find(Integer id) {
		EntityManager manager = openSession();
		return manager.find(Place.class, id);
	}
	
	public boolean destroy(Integer id) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			Place place = find(id);
			manager.remove(manager.contains(place) ? place : manager.merge(place));
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
}
