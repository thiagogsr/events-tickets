package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventstickets.models.EventInscription;
import eventstickets.models.Event;

public class EventInscriptionDAO extends MainDAO{
	public boolean create (EventInscription eventInscriptions){
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
	
	public Long totalSubscribersEvent(Integer eventId){
		EntityManager manager = openSession();
		Query eventInscriptionsQuery = manager.createQuery("select count(*)" +
														   " from eventstickets.models.EventInscription eventInscription" +
														   " where eventInscription.event.id = :eventId");
		
		eventInscriptionsQuery.setParameter("eventId", eventId);
		Long count = (Long)eventInscriptionsQuery.getSingleResult(); 
		return count;
	}
	
	public List<Event> getRegisteredEvents(Integer userId){
		EntityManager manager = openSession();
		Query eventsQuery = manager.createQuery("select event from eventstickets.models.EventInscription eventInscription" +
												" join eventInscription.event event" +
												" where eventInscription.user.id = (:userId)");
		eventsQuery.setParameter("userId", userId);
		List<Event> events = eventsQuery.getResultList();
		return events;
	}
	
	public List<Event> getNotRegisteredEvents(Integer userId){
		EntityManager manager = openSession();
		Query eventsQuery = manager.createQuery("from eventstickets.models.Event event" + 
												" where event.inscriptionsStartDate <= :today" +
												" and event.inscriptionsEndDate >= :today" +
												" and not exists (from eventstickets.models.EventInscription eventInscription" +
												" where eventInscription.user.id = (:userId)" + 
												" and eventInscription.event.id = event.id)");
		
		eventsQuery.setParameter("today", new Date());
		eventsQuery.setParameter("userId", userId);
		List<Event> events = eventsQuery.getResultList();
		return events;
	}
	
	public List getEventsInscriptions(Integer eventId){
		EntityManager manager = openSession();
		Query query = manager.createQuery("SELECT u " +
										  "FROM eventstickets.models.EventInscription ei " +
										  "INNER JOIN ei.event e " +
										  "INNER JOIN ei.user u " +
										  "WHERE e.id = :eventId");
		
		query.setParameter("eventId", eventId);
		List events = query.getResultList();
		return events;
	}

	public List getEventsInscriptionsByEvent(Integer eventId){
		EntityManager manager = openSession();
		Query query = manager.createQuery("SELECT ei " +
										  "FROM eventstickets.models.EventInscription ei " +
										  "INNER JOIN ei.event e " +
										  "WHERE e.id = :eventId");
		query.setParameter("eventId", eventId);
		List events = query.getResultList();
		return events;
	}
}