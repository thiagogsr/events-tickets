package eventstickets.dao;

import java.util.Date;
import java.util.List;

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
	public boolean register(User user){
		EntityManager manager = openSession();
		try{
			user.setRole(Role.PARTICIPANT);
			user.setPassword(UserHelper.getMD5(user.getPassword()));
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e){
			manager.getTransaction().rollback();
			return false;
		} finally{
			manager.close();
		}
	}
	public boolean create(User user){
		EntityManager manager = openSession();
		try{
			user.setPassword(UserHelper.getMD5(user.getPassword()));
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e){
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public boolean destroy(Integer id) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			User user = manager.find(User.class, id);
			manager.remove(manager.contains(user) ? user : manager.merge(user));
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public boolean update(User user) {
		EntityManager manager = openSession();
		try {
			manager.getTransaction().begin();
			user.setPassword(UserHelper.getMD5(user.getPassword()));
			user.setUpdatedAt(new Date());
		    manager.merge(user);
		    manager.getTransaction().commit();
		    return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public List<User> all() {
		EntityManager manager = openSession();
		List<User> users = manager.createQuery("from eventstickets.models.User").getResultList();
		return users;
	}
	
	public List<User> byRole(Role role) {
		EntityManager manager = openSession();
		Query userQuery = manager.createQuery("from eventstickets.models.User user where user.role = :role");
		userQuery.setParameter("role", role);
		List<User> users = userQuery.getResultList();
		return users;
	}

	public User find(Integer id) {
		EntityManager manager = openSession();
		return manager.find(User.class, id);
	}
}
