package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventstickets.models.MiniCourseInscription;

public class MiniCourseInscriptionDAO extends MainDAO {
	public boolean create(MiniCourseInscription miniCourseInscription) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			miniCourseInscription.setCreatedAt(new Date());
			manager.persist(miniCourseInscription);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	public Long totalInsriptionMiniCourse(Integer miniCourseId) {
		EntityManager manager = openSession();
		Query miniCourseInscriptionQuery = manager.createQuery(
				"select count(*) " + "from eventstickets.models.MiniCourseInscription miniCourseInscription "
						+ "where miniCourseInscription.miniCourse.id = :miniCourseId");

		miniCourseInscriptionQuery.setParameter("miniCourseId", miniCourseId);
		Long count = (Long) miniCourseInscriptionQuery.getSingleResult();
		return count;
	}

	public List<MiniCourseInscription> registered(Integer eventId) {
		EntityManager manager = openSession();
		Query miniCourseInscriptionQuery = manager
				.createQuery("from eventstickets.models.MiniCourseInscription miniCourseInscription "
						+ "where miniCourseInscription.miniCourse.event.id = :eventId");

		miniCourseInscriptionQuery.setParameter("eventId", eventId);
		List<MiniCourseInscription> miniCourseInscriptions = miniCourseInscriptionQuery.getResultList();
		return miniCourseInscriptions;
	}
}
