package eventstickets.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventstickets.dao.MainDAO;
import eventstickets.helpers.UserHelper;
import eventstickets.models.Role;
import eventstickets.models.User;

public class UserDAO extends MainDAO {
	public User authenticate(String username, String password) {
		return getByUsernameAndPassword(username, password);
	}

	private User getByUsernameAndPassword(String username, String password) {
		EntityManager manager = openSession();
		User user = null;
		try {
			manager.getTransaction().begin();
			String encryptedPassword = UserHelper.getMD5(password);
			Query query = manager.createQuery("from eventstickets.models.User where username = :username and password = :password");
			query.setParameter("username", username);
			query.setParameter("password", encryptedPassword);
			user = (User) query.getSingleResult();
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return user;
	}
	public void create (User user){
		EntityManager manager = openSession();
		try{
			user.setRole(Role.PARTICIPANT);
			user.setPassword(UserHelper.getMD5(user.getPassword()));
			user.setCreatedAt(new Date());
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		}catch (Exception e){
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
	}
}
