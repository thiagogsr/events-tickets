package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventstickets.models.MiniCourse;
import eventstickets.models.MiniInscriptions;

public class MiniInscriptionsDAO  extends MainDAO{
	public boolean create (MiniInscriptions miniInscriptions){
		EntityManager manager = openSession();
		try{
			manager.getTransaction().begin();
			miniInscriptions.setCreatedAt(new Date());
			manager.persist(miniInscriptions);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e){
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Long totalInsriptionMini (Integer miniId){
		EntityManager manager = openSession();
		Query miniInscriptionsQuery = manager.createQuery("select count(*)" +
															" from eventstickets.models.MiniInscriptions miniInscriptions" +
															" where miniInscriptions.mini.id = :miniId");
		miniInscriptionsQuery.setParameter("miniId", miniId);
		Long count = (Long)miniInscriptionsQuery.getSingleResult(); 
		return count;
	}
	
	public List<MiniCourse> getMiniCourseByUserId(Integer userId){
		EntityManager manager = openSession();
		Query miniQuery = manager.createQuery("from eventstickets.models.MiniCourse mini" + 
												" where not exists (from eventstickets.models.MiniInscriptions miniInscriptions" +
																	" where miniInscriptions.user.id in (:userId)" + 
																	" and" + 
																	" miniInscriptions.mini.id = mini.id)");
		miniQuery.setParameter("userId", userId);
		List<MiniCourse> events = miniQuery.getResultList();
		return events;
	}

	public List<MiniInscriptions> all(){
		EntityManager manager = openSession();
		List<MiniInscriptions> miniCourse = manager.createQuery("from eventstickets.models.MiniInscriptions").getResultList();
		return miniCourse;
	}
	
}
