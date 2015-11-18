package eventstickets.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventstickets.models.MiniCourse;
import eventstickets.models.MiniCourseInscriptions;

public class MiniCourseInscriptionsDAO  extends MainDAO{
	public boolean create (MiniCourseInscriptions miniCourseInscriptions){
		EntityManager manager = openSession();
		try{
			manager.getTransaction().begin();
			miniCourseInscriptions.setCreatedAt(new Date());
			manager.persist(miniCourseInscriptions);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e){
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Long totalInsriptionMiniCourse (Integer miniCourseId){
		EntityManager manager = openSession();
		Query miniCourseInscriptionsQuery = manager.createQuery("select count(*)" +
															" from eventstickets.models.MiniCourseInscriptions miniCourseInscriptions" +
															" where miniCourseInscriptions.miniCourse.id = :miniCourseId");
		miniCourseInscriptionsQuery.setParameter("miniCourseId", miniCourseId);
		Long count = (Long)miniCourseInscriptionsQuery.getSingleResult(); 
		return count;
	}
	
	public List<MiniCourse> getMiniCourseByUserId(Integer userId){
		EntityManager manager = openSession();
		Query miniCourseQuery = manager.createQuery("from eventstickets.models.MiniCourse miniCourse" + 
												" where not exists (from eventstickets.models.MiniCourseInscriptions miniCourseInscriptions" +
																	" where miniCourseInscriptions.user.id in (:userId)" + 
																	" and" + 
																	" miniCourseInscriptions.miniCourse.id = miniCourse.id)");
		miniCourseQuery.setParameter("userId", userId);
		List<MiniCourse> miniCourse = miniCourseQuery.getResultList();
		return miniCourse;
	}

	public List<MiniCourseInscriptions> all(){
		EntityManager manager = openSession();
		List<MiniCourseInscriptions> miniCourse = manager.createQuery("from eventstickets.models.MiniCourseInscriptions").getResultList();
		return miniCourse;
	}
	
}
