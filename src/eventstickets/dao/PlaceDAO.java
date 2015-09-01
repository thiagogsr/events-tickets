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
	
	public void create(Place place, User currentUser) {
		EntityManager manager = openSession();
		try {
			place.setCreatedBy(currentUser);
			place.setCreatedAt(new Date());
		
		    manager.getTransaction().begin();    
		    manager.persist(place);
		    manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}
}
