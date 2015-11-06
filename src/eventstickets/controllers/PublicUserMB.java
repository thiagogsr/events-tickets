package eventstickets.controllers;

import java.io.Serializable;


import javax.faces.bean.*;

import eventstickets.dao.UserDAO;
import eventstickets.models.User;

@ManagedBean (name = "publicUserMB")
@RequestScoped
public class PublicUserMB implements Serializable{
	private static final long serialVersionUID = 1L;
	private User user = new User();
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String create(){
		UserDAO dao = new UserDAO();
		
		if (dao.register(user)) {
			return "login.jsp";
		} else {
			return "singUp";
		}
	}
}
