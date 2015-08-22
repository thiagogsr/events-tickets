package eventstickets.tasks;

import java.util.Date;

import javax.persistence.EntityManager;

import eventstickets.dao.MainDAO;
import eventstickets.helpers.UserHelper;
import eventstickets.models.Role;
import eventstickets.models.User;

public class SetupDatabase {
	public static void main(String[] args) {
		MainDAO dao = new MainDAO();
		EntityManager manager = dao.openSession();
		
		// seed
		createUser(manager);

		manager.close();
	}

	public static void createUser(EntityManager manager) {
	    User user = new User();
	    user.setName("Thiago Guimaraes");
	    user.setUsername("thiago");
	    user.setPassword(UserHelper.getMD5("123456"));
	    user.setEmail("thiagogsr@gmail.com");
	    user.setRole(Role.ADMIN);
	    user.setCreatedAt(new Date());
	    user.setUpdatedAt(new Date());
	
	    manager.getTransaction().begin();    
	    manager.persist(user);
	    manager.getTransaction().commit();
	}
}
