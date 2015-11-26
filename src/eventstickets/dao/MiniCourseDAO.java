package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventstickets.models.MiniCourse;
import eventstickets.models.User;

public class MiniCourseDAO extends MainDAO {
	public boolean create(MiniCourse miniCourse, User currentUser) {
		EntityManager manager = openSession();

		try {
			miniCourse.setCreatedAt(new Date());
			miniCourse.setCreatedBy(currentUser);
			manager.getTransaction().begin();
			manager.persist(miniCourse);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	public boolean update(MiniCourse minicourse) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			manager.merge(minicourse);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	public MiniCourse find(Integer id) {
		EntityManager manager = openSession();
		return manager.find(MiniCourse.class, id);
	}

	public boolean destroy(Integer id) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			MiniCourse minicourse = find(id);
			manager.remove(manager.contains(minicourse) ? minicourse : manager.merge(minicourse));
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	public List<MiniCourse> all() {
		EntityManager manager = openSession();
		List<MiniCourse> miniCourses = manager.createQuery("from eventstickets.models.MiniCourse").getResultList();
		return miniCourses;
	}

	public List<MiniCourse> notRegistered(Integer userId, Integer eventId) {
		EntityManager manager = openSession();
		Query miniCourseQuery = manager.createQuery("from eventstickets.models.MiniCourse miniCourse" +
				" where miniCourse.event.id = :eventId" +
				" and not exists (from eventstickets.models.MiniCourseInscription miniCourseInscription" +
				" where miniCourseInscription.participant.id = :userId" +
				" and miniCourseInscription.miniCourse.id = miniCourse.id)");

		miniCourseQuery.setParameter("userId", userId);
		miniCourseQuery.setParameter("eventId", eventId);
		List<MiniCourse> miniCourses = miniCourseQuery.getResultList();
		return miniCourses;
	}
	
	public List getMiniCoursesByEvent(Integer eventId){
		EntityManager manager = openSession();
		Query query = manager.createQuery("SELECT miniCourse " +
										  "FROM eventstickets.models.MiniCourse miniCourse " +
										  "INNER JOIN miniCourse.event e " +
										  "WHERE e.id = :eventId");
		query.setParameter("eventId", eventId);
		List events = query.getResultList();
		return events;
	}
}