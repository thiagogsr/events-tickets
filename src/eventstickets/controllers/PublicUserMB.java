package eventstickets.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
	
	public void create() throws IOException{
		UserDAO dao = new UserDAO();
		
		if (dao.register(user)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsp?success=true");
		}
	}
}
