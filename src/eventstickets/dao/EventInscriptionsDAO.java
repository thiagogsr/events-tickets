package eventstickets.dao;

import java.util.Date;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import eventstickets.models.EventInscriptions;
import eventstickets.models.Event;

public class EventInscriptionsDAO extends MainDAO{
	public boolean create (EventInscriptions eventInscriptions){
		EntityManager manager = openSession();
		try{
			manager.getTransaction().begin();
			eventInscriptions.setCreatedAt(new Date());
			manager.persist(eventInscriptions);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e){
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Long totalSubscribersEvent (Integer eventId){
		EntityManager manager = openSession();
		Query eventInscriptionsQuery = manager.createQuery("select count(*)" +
															" from eventstickets.models.EventInscriptions eventInscriptions" +
															" where eventInscriptions.event.id = :eventId");
		eventInscriptionsQuery.setParameter("eventId", eventId);
		Long count = (Long)eventInscriptionsQuery.getSingleResult(); 
		return count;
	}
	
	public List<Event> getEventByUserId(Integer userId){
		EntityManager manager = openSession();
		Query eventsQuery = manager.createQuery("from eventstickets.models.Event event" + 
												" where not exists (from eventstickets.models.EventInscriptions eventInscriptions" +
																	" where eventInscriptions.user.id in (:userId)" + 
																	" and" + 
																	" eventInscriptions.event.id = event.id)");
		eventsQuery.setParameter("userId", userId);
		List<Event> events = eventsQuery.getResultList();
		return events;
	}
}
