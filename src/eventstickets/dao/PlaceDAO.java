package eventstickets.dao;

import java.util.List;

import javax.persistence.EntityManager;

import eventstickets.dao.MainDAO;
import eventstickets.models.Place;

public class PlaceDAO extends MainDAO {
	public List<Place> all() {
		EntityManager manager = openSession();
		List<Place> places = manager.createQuery("from eventstickets.models.Place").getResultList();
		return places;
	}
}
