package eventstickets.controllers;

import javax.faces.bean.*;

import eventstickets.dao.UserDAO;
import eventstickets.models.User;

@ManagedBean (name = "userMB")
@ViewScoped
public class UserMB {
	private User user = new User();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void create(){
		UserDAO dao = new UserDAO();
		dao.create(user);
	}
}
